package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.Package;
import ts.model.TransHistory;

public class PackageDao extends BaseDao<Package, String> {

	private TransNodeDao transNodeDao;
	private TransHistoryDao transHistoryDao;

	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao transNodeDao) {
		this.transNodeDao = transNodeDao;
	}

	public TransHistoryDao getTransHistoryDao() {
		return transHistoryDao;
	}

	public void setTransHistoryDao(TransHistoryDao transHistoryDao) {
		this.transHistoryDao = transHistoryDao;
	}

	public PackageDao() {
		super(Package.class);
	}

	@Override
	public Package get(String id) {

		Package pkg = super.get(id);
		if (pkg == null) {
			System.out.println("pkgDao==null");
		} else {
		}
		if (pkg != null) {
			if (pkg.getSourcenode() != null && !pkg.getSourcenode().equals("")) {
				pkg.setSourceTransNode(transNodeDao.get(pkg.getSourcenode()));
			}
			if (pkg.getTargetnode() != null && !pkg.getTargetnode().equals("")) {
				pkg.setTargetTransNode(transNodeDao.get(pkg.getTargetnode()));
			}
		}
		return pkg;
	}

	public List<Package> getPackageListByUid(int uid) {
		List<TransHistory> th_list = transHistoryDao.findBy("uidto", uid, "sn",
				true);
		List<Package> pkg_list = new ArrayList<Package>();
		for (TransHistory t : th_list) {
			Package p = get(t.getPackageid().getId());
			if (p.getStatus() == Package.STATUS.STATUS_TRANSPORT) {
				pkg_list.add(p);
			}
		}
		System.out.println("++++uid包裹列表：" + pkg_list);
		return pkg_list;
	}
}
