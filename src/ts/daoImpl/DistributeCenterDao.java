package ts.daoImpl;

import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.DistributeCenter;

public class DistributeCenterDao extends BaseDao<DistributeCenter, Integer> {
	public DistributeCenterDao() {
		super(DistributeCenter.class);
	}

	List<DistributeCenter> list;

	public DistributeCenter getPkgByEsid(String id) {
		try {
			list = findBy("expresssheetid", id, "sn", true);
		} catch (Exception e) {
			return null;
		}
		return list.get(0);
	}
}
