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
			String restrictions, String value) {
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		switch (restrictions.toLowerCase()) {
		case "eq":
			list = expressSheetDao.findBy(property, value, "ID", true);
			break;
		case "like":
			list = expressSheetDao.findLike(property, value + "%", "ID", true);
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
		System.out.println(es.toString());
		return Response.ok(es).header("EntityClass", "ExpressSheet").build();
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
			// if(es.getStatus() != 0)
			// return Response.ok(es).header("EntityClass",
			// "L_ExpressSheet").build(); //已经存在,且不能更改
			// else
			return Response.ok(es).header("EntityClass", "E_ExpressSheet")
					.build(); // 已经存在
		}
		try {
			System.out.println("***start try****");
			// String pkgId = userDao.get(uid).getReceivepid();
			User user = userDao.get(uid);
			System.out.println("***start try****" + user.toString());
			String pkgId = user.getReceivepid();
			System.out.println("***start try****" + pkgId);
			ExpressSheet nes = new ExpressSheet();
			nes.setId(id);
			nes.setExpresstype(0);
			nes.setAccepter(String.valueOf(uid));
			nes.setAcceptetime(tm);
			nes.setStatus(ExpressSheet.STATUS.STATUS_CREATED);
			expressSheetDao.save(nes);
			DistributeCenter pkg_add = new DistributeCenter();
			pkg_add.setPackageid(pkgId);
			pkg_add.setExpressSheetID(id);
			distributeCenterDao.save(pkg_add);
			return Response.ok(nes).header("EntityClass", "ExpressSheet")
					.build();
		} catch (Exception e) {
			System.out.println("***try error****");
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response saveExpressSheet(ExpressSheet obj) {
		try {
			expressSheetDao.save(obj);
			return Response.ok(obj).header("EntityClass", "R_ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response ReceiveExpressSheetId(String id, int uid) {
		try {
			ExpressSheet nes = expressSheetDao.get(id);
			nes.setAccepter(String.valueOf(uid));
			nes.setAcceptetime(new Date());
			nes.setStatus(ExpressSheet.STATUS.STATUS_RECEIVED);
			expressSheetDao.save(nes);
			// TransPackage tp = new TransPackage();

			// transPackageDao.save(tp);
			return Response.ok(nes).header("EntityClass", "ExpressSheet")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response DispatchExpressSheet(String id, int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response DeliveryExpressSheetId(String obj, int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Package> getPackageList(String property, String restrictions,
			String value) {
		List<Package> list = new ArrayList<Package>();
		switch (restrictions.toLowerCase()) {
		case "eq":
			list = packageDao.findBy(property, value, "ID", true);
			break;
		case "like":
			list = packageDao.findLike(property, value + "%", "ID", true);
			break;
		}
		return list;
	}

	@Override
	public Response getTransPackage(String id) {
		Package es = packageDao.get(id);
		return Response.ok(es).header("EntityClass", "TransPackage").build();
	}

	@Override
	public Response newTransPackage(String id, int uid) {
		try {
			Package npk = new Package();
			npk.setId(id);
			// npk.setStatus(value);
			npk.setCreatetime(new Date());
			packageDao.save(npk);
			return Response.ok(npk).header("EntityClass", "TransPackage")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response savePackage(Package obj) {
		try {
			packageDao.save(obj);
			return Response.ok(obj).header("EntityClass", "R_TransPackage")
					.build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
