package ts.daoImpl;

import java.sql.Timestamp;

import ts.daoBase.BaseDao;
import ts.model.ExpressSheet;
import ts.model.Package;
import ts.model.TransHistory;
import ts.model.User;

public class TransHistoryDao extends BaseDao<TransHistory, Integer> {

	private UserDao userDao;
	private PackageDao packageDao;
	private DistributeCenterDao distributeCenterDao;

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

	public void add(ExpressSheet es) {
		TransHistory th_add = new TransHistory();
		User user = userDao.get(Integer.valueOf(es.getAccepter()));
		th_add.setUidto(Integer.valueOf(es.getAccepter()));
		th_add.setUidfrom(Integer.valueOf(es.getAccepter()));
		th_add.setActtime(new Timestamp(new java.util.Date().getTime()));
		String pid = user.getReceivepid();
		Package Pkg = packageDao.get(pid);
		th_add.setPackageid(Pkg);
		try {
			save(th_add);
		} catch (Exception e) {
		}
	}
}
