package ts.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.DistributeCenter;
import ts.model.ExpressSheet;
import ts.model.Package;
import ts.model.TransHistory;
import ts.model.User;

public class TransHistoryDao extends BaseDao<TransHistory, Integer> {

	private UserDao userDao;
	private PackageDao packageDao;
	private DistributeCenterDao distributeCenterDao;
	private ExpressSheetDao expressSheetDao;

	public ExpressSheetDao getExpressSheetDao() {
		return expressSheetDao;
	}

	public void setExpressSheetDao(ExpressSheetDao expressSheetDao) {
		this.expressSheetDao = expressSheetDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PackageDao getPackageDao() {
		return packageDao;
	}

	public void setPackageDao(PackageDao packageDao) {
		this.packageDao = packageDao;
	}

	public DistributeCenterDao getDistributeCenterDao() {
		return distributeCenterDao;
	}

	public void setDistributeCenterDao(DistributeCenterDao distributeCenterDao) {
		this.distributeCenterDao = distributeCenterDao;
	}

	public TransHistoryDao() {
		super(TransHistory.class);
	}

	public void addRcvEsHis(ExpressSheet es, Date tm) {
		TransHistory th_add = new TransHistory();
		th_add.setActtime(tm);
		User user = userDao.get(Integer.valueOf(es.getAccepter()));
		th_add.setUidto(Integer.valueOf(es.getAccepter()));
		th_add.setUidfrom(Integer.valueOf(es.getAccepter()));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		// Date tm = new Date();
		// try {
		// tm = sdf.parse(sdf.format(new Date().getTime()));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }

		String pid = user.getReceivepid();
		Package Pkg = packageDao.get(pid);
		th_add.setPackageid(Pkg);
		try {
			save(th_add);
		} catch (Exception e) {
		}
	}

	public void addDisEsHis(String pid, int uid, Date tm) {
		TransHistory th_add = new TransHistory();
		th_add.setActtime(tm);
		th_add.setUidto(uid);
		th_add.setUidfrom(uid);
		Package Pkg = packageDao.get(pid);
		th_add.setPackageid(Pkg);
		try {
			save(th_add);
		} catch (Exception e) {
		}
	}

	public void addDlvHis(String pid, int uid, Date tm) {
		Package dlvPkg = packageDao.get(pid);
		TransHistory th_add = new TransHistory();
		th_add.setActtime(tm);
		th_add.setPackageid(dlvPkg);
		th_add.setUidfrom(uid);
		th_add.setUidto(0);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		// Date tm = new Date();
		// try {
		// tm = sdf.parse(sdf.format(new Date().getTime()));
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }

		try {
			save(th_add);
		} catch (Exception e) {
		}
	}

	public void addPkgHis(String pid, int uid) {
		Package p = packageDao.get(pid);
		TransHistory th_add = new TransHistory();
		if (p.getTransHistory().isEmpty()) {
			th_add.setUidfrom(uid);
		} else {
			th_add.setUidfrom(pkgUidFrom(pid));
		}
		th_add.setUidto(uid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date tm = new Date();
		try {
			tm = sdf.parse(sdf.format(new Date().getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		th_add.setActtime(tm);

		th_add.setPackageid(p);
		try {
			save(th_add);
		} catch (Exception e) {
		}
	}

	public int pkgUidFrom(String pid) {
		Package p = packageDao.get(pid);
		List<TransHistory> list_his = p.getTransHistory();
		TransHistory t = list_his.get(list_his.size() - 1);
		return t.getUidto();
	}

	public List<TransHistory> getTransHistoryListByEid(String eid) {

		List<TransHistory> list_hs = new ArrayList<TransHistory>();
		List<Package> list_pkg = new ArrayList<Package>();
		TransHistory actHis = new TransHistory();
		TransHistory dlvHis = new TransHistory();
		TransHistory dispHis = new TransHistory();
		List<DistributeCenter> list_dc = distributeCenterDao
				.getDistributerByEsid(eid);
		System.out.println("*******dc: " + list_dc.toString());
		for (DistributeCenter dc : list_dc) {
			list_pkg.add(packageDao.get(dc.getPackageid()));
		}
		System.out.println("*******pkg: " + list_pkg.toString());
		List<TransHistory> list_his0 = list_pkg.get(0).getTransHistory();
		List<TransHistory> list_hislast = list_pkg.get(list_pkg.size() - 1)
				.getTransHistory();
		System.out.println("*******firsthis: " + list_his0.toString());
		Date ercvTime = expressSheetDao.get(eid).getAcceptetime();
		for (TransHistory his : list_his0) {
			if (his.getActtime().equals(ercvTime)) {
				actHis = his;
				list_hs.add(actHis);
				break;
			}
		}
		Date edlvTime = expressSheetDao.get(eid).getDelivetime();
		for (TransHistory his : list_hislast) {
			if (his.getActtime().equals(edlvTime)) {
				dlvHis = his;
				break;
			}
		}
		for (TransHistory his : list_hislast) {
			if (his.getUidto() == his.getUidfrom()) {
				dispHis = his;
				break;
			}
		}
		list_hs.add(list_his0.get(list_his0.size() - 1));
		if (list_pkg.get(list_pkg.size() - 1).getId().length() == 4) {
			for (int i = 1; i < list_pkg.size() - 1; i++) {
				list_hs.addAll(list_pkg.get(i).getTransHistory());
			}
			list_hs.add(dispHis);
			list_hs.add(dlvHis);
		} else {

			for (int i = 1; i < list_pkg.size(); i++) {
				list_hs.addAll(list_pkg.get(i).getTransHistory());
			}
		}
		List<TransHistory> list_nhis = new ArrayList<TransHistory>();
		for (TransHistory h : list_hs) {
			list_nhis.add(get(h.getORMId()));
		}
		System.out.println("*******nhis: " + list_nhis.toString());

		return list_nhis;

	}

	@Override
	public TransHistory get(Integer id) {
		// TODO Auto-generated method stub
		TransHistory his = super.get(id);
		if (his != null) {
			his.setUserFrom(userDao.get(his.getUidfrom()));
			his.setUserTo(userDao.get(his.getUidto()));
			if (his.getPackageid() != null) {
				his.setPackageid(packageDao.get(his.getPackageid().getId()));
			}
		}
		return his;
	}
}
