package ts.serviceImpl;

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
			System.out.println(es.toString());
			return Response.ok(es).header("EntityClass", "ExpressSheet")
					.build();
		} else {
			return Response.ok(es).header("EntityClass", "N_ExpressSheet")
					.build();// 不存在此运单
		}
	}

	@Override
	public Response newExpressSheet(String id, int uid) {
		// 产生一个不带毫秒的时间,不然,SQL时间和JAVA时间格式不一致
		System.out.println("***start new exp****\n" + "expid: " + id + " uid:"
				+ uid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date tm = new Date();
		try {
			tm = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

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
				nes.setAcceptetime(tm);
				// nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
				nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
				expressSheetDao.save(nes);
				DistributeCenter pkg_add = new DistributeCenter();
				// 为什么在新建运单的过程中，直接将快件放入user的揽收包裹中？
				// 可是下面还有一个方法是专门用来揽收快件的？
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
			obj.setStatus(ExpressSheet.STATUS.STATUS_RECEIVED);

			System.out.println("+++add done+++");
			expressSheetDao.save(obj);
			System.out.println("+++add done+++");
			transHistoryDao.add(obj);
			return Response.ok(obj).header("EntityClass", "R_ExpressSheet")
					.build();
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
			nes.setAcceptetime(new Date());
			nes.setStatus(ExpressSheet.STATUS.STATUS_RECEIVED);
			expressSheetDao.save(nes);
			return Response.ok(nes).header("EntityClass", "ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response DispatchExpressSheet(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			if (nes != null) {
				User user = userDao.get(uid);
				String pkgId = user.getDeliverpid();
				if (!pkgId.equals("")) {
					nes.setDeliver(String.valueOf(uid));
					// nes.setDelivetime(new Date());
					nes.setStatus(ExpressSheet.STATUS.STATUS_DISPATCHED);
					expressSheetDao.save(nes);
					DistributeCenter pkg_add = new DistributeCenter();
					// 为什么在新建运单的过程中，直接将快件放入user的揽收包裹中？
					// 可是下面还有一个方法是专门用来揽收快件的？
					pkg_add.setPackageid(pkgId);
					pkg_add.setExpressSheetID(id);
					distributeCenterDao.save(pkg_add);
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

	@Override
	public Response DeliveryExpressSheetId(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			// nes.setDeliver(String.valueOf(uid));
			nes.setDelivetime(new Date());
			nes.setStatus(ExpressSheet.STATUS.STATUS_DELIVERIED);
			expressSheetDao.save(nes);
			return Response.ok(nes).header("EntityClass", "ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

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

	@Override
	public Response getTransPackage(String id) {
		Package es = packageDao.get(id);
		return Response.ok(es).header("EntityClass", "Package").build();
	}

	@Override
	public Response newTransPackage(String id) {
		try {
			Package npk = new Package();
			npk.setId(id);
			npk.setCreatetime(new Date());
			npk.setStatus(Package.STATUS.STATUS_CREATED);
			packageDao.save(npk);
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
}
