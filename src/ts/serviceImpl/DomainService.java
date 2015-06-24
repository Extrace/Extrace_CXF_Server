package ts.serviceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import ts.daoImpl.DistributeCenterDao;
import ts.daoImpl.ExpressSheetDao;
import ts.daoImpl.PackageDao;
import ts.daoImpl.TransHistoryDao;
import ts.daoImpl.UserDao;
import ts.model.DistributeCenter;
import ts.model.ExpressSheet;
import ts.model.Package;
import ts.model.TransHistory;
import ts.model.User;
import ts.serviceInterface.IDomainService;

public class DomainService implements IDomainService {

	private ExpressSheetDao expressSheetDao;
	private PackageDao packageDao;
	private TransHistoryDao transHistoryDao;
	private DistributeCenterDao distributeCenterDao;

	private UserDao userDao;

	public ExpressSheetDao getExpressSheetDao() {
		return expressSheetDao;
	}

	public void setExpressSheetDao(ExpressSheetDao dao) {
		this.expressSheetDao = dao;
	}

	public PackageDao getPackageDao() {
		return packageDao;
	}

	public void setPackageDao(PackageDao dao) {
		this.packageDao = dao;
	}

	public TransHistoryDao getTransHistoryDao() {
		return transHistoryDao;
	}

	public void setTransHistoryDao(TransHistoryDao dao) {
		this.transHistoryDao = dao;
	}

	public DistributeCenterDao getDistributeCenterDao() {
		return distributeCenterDao;
	}

	public void setDistributeCenterDao(DistributeCenterDao dao) {
		this.distributeCenterDao = dao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}

	@Override
	public List<ExpressSheet> getExpressList(String property,
			String restrictions, String value) { // id like 6
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		switch (restrictions.toLowerCase()) {
		case "eq":
			list = expressSheetDao.findBy(property, value, "id", true);
			break;
		case "like":
			list = expressSheetDao.findLike(property, value + "%", "id", true);
			break;
		}
		return list;
	}

	@Override
	public List<ExpressSheet> getExpressListInPackage(String packageId) {
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		list = expressSheetDao.getListInPackage(packageId);
		return list;
	}

	@Override
	public Response getExpressSheet(String id) {
		ExpressSheet es = expressSheetDao.get(id);
		if (es != null) {
			return Response.ok(es).header("EntityClass", "ExpressSheet")
					.build();
		} else {
			return Response.ok(es).header("EntityClass", "N_ExpressSheet")
					.build();// 不存在此运单
		}
	}

	@Override
	public Response newExpressSheet(String id, int uid) {
		// // 产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
		// System.out.println("***start new exp****\n" + "expid: " + id +
		// " uid:"
		// + uid);

		ExpressSheet es = null;
		try {
			es = expressSheetDao.get(id);
		} catch (Exception e1) {
		}

		if (es != null) {
			return Response.ok(es).header("EntityClass", "E_ExpressSheet")
					.build(); // 已经存在
		}
		try {
			User user = userDao.get(uid);
			ExpressSheet nes = new ExpressSheet();
			String pkgId = user.getReceivepid();
			if (!pkgId.equals("")) {
				nes.setId(id);
				nes.setExpresstype(0);
				nes.setAccepter(String.valueOf(uid));
				// nes.setAcceptetime(tm);
				nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
				expressSheetDao.save(nes);
				DistributeCenter pkg_add = new DistributeCenter();
				pkg_add.setPackageid(pkgId);
				pkg_add.setExpressSheetID(id);
				distributeCenterDao.save(pkg_add);
				return Response.ok(nes).header("EntityClass", "ExpressSheet")
						.build();
			} else {
				return Response.ok(nes)
						.header("EntityClass", "NP_ExpressSheet").build();
			}
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response saveExpressSheet(ExpressSheet obj) {

		try {
			if (obj.getStatus() == ExpressSheet.STATUS.STATUS_CREATED) {
				obj.setStatus(ExpressSheet.STATUS.STATUS_RECEIVED);
				// 产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				Date tm = new Date();
				try {
					tm = sdf.parse(sdf.format(new Date().getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				obj.setAcceptetime(tm);
				transHistoryDao.addRcvEsHis(obj, tm);
				expressSheetDao.save(obj);
				return Response.ok(obj).header("EntityClass", "R_ExpressSheet")
						.build();
			} else {
				return Response.ok(obj)
						.header("EntityClass", "RN_ExpressSheet").build();
			}
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/**
	 * 揽收快件？
	 */
	@Override
	public Response ReceiveExpressSheetId(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			nes.setAccepter(String.valueOf(uid));
			nes.setAcceptetime(new Timestamp(new Date().getTime()));
			nes.setStatus(ExpressSheet.STATUS.STATUS_RECEIVED);
			expressSheetDao.save(nes);
			return Response.ok(nes).header("EntityClass", "ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// 派送快件
	@Override
	public Response DispatchExpressSheet(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			if (nes != null
					&& nes.getStatus() == ExpressSheet.STATUS.STATUS_PARTATION) {
				User user = userDao.get(uid);
				String pkgId = user.getDeliverpid();
				if (!pkgId.equals("")) {
					nes.setDeliver(String.valueOf(uid));
					// nes.setDelivetime(new Date());
					nes.setStatus(ExpressSheet.STATUS.STATUS_DISPATCHED);
					expressSheetDao.save(nes);
					DistributeCenter pkg_add = new DistributeCenter();
					pkg_add.setPackageid(pkgId);
					pkg_add.setExpressSheetID(id);
					distributeCenterDao.save(pkg_add);
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd'T'HH:mm:ss");
					Date tm = new Date();
					try {
						tm = sdf.parse(sdf.format(new Date().getTime()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					transHistoryDao.addDisEsHis(pkgId, uid, tm);
					return Response.ok(nes)
							.header("EntityClass", "D_ExpressSheet").build();
				} else {
					return Response.ok(nes)
							.header("EntityClass", "DP_ExpressSheet").build();
				}
			} else {
				return Response.ok(nes).header("EntityClass", "N_ExpressSheet")
						.build();
			}
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// 交付快件
	@Override
	public Response DeliveryExpressSheetId(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			nes.setDeliver(String.valueOf(uid));
			User duser = userDao.get(uid);
			String dpkgid = duser.getDeliverpid();
			// 产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date tm = new Date();
			try {
				tm = sdf.parse(sdf.format(new Date().getTime()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			nes.setDelivetime(tm);
			transHistoryDao.addDlvHis(dpkgid, uid, tm);
			nes.setStatus(ExpressSheet.STATUS.STATUS_DELIVERIED);
			expressSheetDao.save(nes);
			return Response.ok(nes).header("EntityClass", "DLV_ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// 获取包裹列表
	@Override
	public List<Package> getPackageList(String property, String restrictions,
			String value) {
		List<Package> list = new ArrayList<Package>();
		switch (restrictions.toLowerCase()) {
		case "eq":
			list = packageDao.findBy(property, value, "id", true);
			break;
		case "like":
			list = packageDao.findLike(property, value + "%", "id", true);
			break;
		}
		return list;
	}

	// 获取单个包裹
	@Override
	public Response getTransPackage(String id) {
		Package es = packageDao.get(id);
		return Response.ok(es).header("EntityClass", "Package").build();
	}

	// 新建包裹
	@Override
	public Response newTransPackage(String id, int uid) {

		// 产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致

		Package pk = null;
		try {
			pk = packageDao.get(id);
			System.out.println("Domain Service:pk 获取失败" + pk.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pk != null) {
			return Response.ok(pk).header("EntityClass", "E_Package").build();
		}
		try {
			Package npk = new Package();
			npk.setId(id);
			npk.setUid(uid);
			npk.setStatus(Package.STATUS.STATUS_CREATED);
			System.out.println("before save package!");
			packageDao.save(npk);
			System.out.println("have save package!");
			return Response.ok(npk).header("EntityClass", "Package").build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response savePackage(Package obj) {
		try {
			packageDao.save(obj);
			return Response.ok(obj).header("EntityClass", "R_Package").build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// 根据包裹ID拆包，放入转运包裹
	@Override
	public Response UnpackExpressListInPackage(String packageId, int uid) {
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		Package pkg = packageDao.get(packageId);
		if (pkg.getStatus() != Package.STATUS.STATUS_PARTATION) {
			pkg.setStatus(Package.STATUS.STATUS_PARTATION);
			list = expressSheetDao.getListInPackage(packageId);
			User user = userDao.get(uid);
			String transPid = user.getTranspid();
			for (ExpressSheet es : list) {
				DistributeCenter pkg_add = new DistributeCenter();
				// 添加包裹和快件的联系
				pkg_add.setExpressSheetID(es.getId());
				pkg_add.setPackageid(transPid);
				// 将要拆包的包裹内的快件状态改为“分拣”
				es.setStatus(ExpressSheet.STATUS.STATUS_PARTATION);
				distributeCenterDao.save(pkg_add);
				expressSheetDao.save(es);
			}
			// 加入拆包历史
			transHistoryDao.addPkgHis(packageId, uid);
			// transHistoryDao.addPkgHis(user.getTranspid(), uid);
			return Response.ok(pkg).header("EntityClass", "UNP_Package")
					.build();
		} else {
			return Response.ok(pkg).header("EntityClass", "NULL_Package")
					.build();
		}
	}

	@Override
	public Response AddExpressSheet(String id, String pid, int uid) {
		DistributeCenter pkg_add = new DistributeCenter();
		ExpressSheet es = expressSheetDao.get(id);
		if (es != null) {
			es.setStatus(ExpressSheet.STATUS.STATUS_TRANSPORT);
			pkg_add.setExpressSheetID(id);
			pkg_add.setPackageid(pid);
			distributeCenterDao.save(pkg_add);
			System.out.println("***加入包裹成功***");
			return Response.ok(es).header("EntityClass", "AD_ExpressSheet")
					.build();
		} else {
			return Response.ok(es).header("EntityClass", "NAD_ExpressSheet")
					.build();
		}
	}

	@Override
	public Response packTransPackage(String id, int uid) {
		Package p = packageDao.get(id);
		p.setStatus(Package.STATUS.STATUS_PACKED);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date tm = new Date();
		try {
			tm = sdf.parse(sdf.format(new Date().getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		p.setCreatetime(tm);
		transHistoryDao.addPkgHis(id, uid);
		packageDao.save(p);
		return Response.ok(p).header("EntityClass", "P_Package").build();
	}

	@Override
	public List<TransHistory> getTransHistoryList(String eid) {
		List<TransHistory> list_his = transHistoryDao
				.getTransHistoryListByEid(eid);
		return list_his;
	}

	@Override
	public List<Package> getPackageListByUid(int uid) {
		return packageDao.getPackageListByUid(uid);
	}

	@Override
	public Response receivePackageByUid(String id, int uid) {
		Package p = packageDao.get(id);
		p.setStatus(Package.STATUS.STATUS_TRANSPORT);
		transHistoryDao.addPkgHis(id, uid);
		packageDao.save(p);
		return Response.ok(p).header("EntityClass", "RCV_Package").build();
	}
}
