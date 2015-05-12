package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.Package;

public class PackageDao extends BaseDao<Package,String> {
	public PackageDao(){
		super(Package.class);
	}
}
