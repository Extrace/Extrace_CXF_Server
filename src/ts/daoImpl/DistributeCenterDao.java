package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.DistributeCenter;

public class DistributeCenterDao extends BaseDao<DistributeCenter, Integer> {
	public DistributeCenterDao() {
		super(DistributeCenter.class);
	}

	List<DistributeCenter> list;

	public List<DistributeCenter> getDistributerByEsid(String id) {
		System.out.println("*****esid: " + id);
		return findBy("expresssheetid", id, "sn", true);
	}

	public void unPack(String pkgId) {
		List<DistributeCenter> list = new ArrayList<DistributeCenter>();
		list = findBy("packageid", pkgId, "sn", true);
		for (DistributeCenter dc : list) {
			remove(dc);
		}
	}

	public void unPackbyEsId(String eid) {
		List<DistributeCenter> list = new ArrayList<DistributeCenter>();
		list = findBy("expresssheetid", eid, "sn", true);
		for (DistributeCenter dc : list) {
			remove(dc);
		}
	}

}
