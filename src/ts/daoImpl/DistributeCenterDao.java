package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.DistributeCenter;

public class DistributeCenterDao extends BaseDao<DistributeCenter,Integer> {
	public DistributeCenterDao(){
		super(DistributeCenter.class);
	}
}
