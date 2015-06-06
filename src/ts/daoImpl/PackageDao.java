package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.Package;

public class PackageDao extends BaseDao<Package, String> {

	private TransNodeDao transNodeDao;

	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao transNodeDao) {
		this.transNodeDao = transNodeDao;
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
			// System.out.println(pkg.toString());
		}
		if (pkg != null) {
			if (pkg.getSourcenode() != null) {
				pkg.setSourceTransNode(transNodeDao.get(pkg.getSourcenode()));
			}
			if (pkg.getTargetnode() != null) {
				pkg.setTargetTransNode(transNodeDao.get(pkg.getTargetnode()));
			}
		}
		// System.out.println(pkg.toString());
		return pkg;

	}
}
