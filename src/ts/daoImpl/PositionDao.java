package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.Position;
import ts.model.TransNode;

public class PositionDao extends BaseDao<Position, Integer> {

	Position p;
	TransNodeDao transNodeDao;

	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao transNodeDao) {
		this.transNodeDao = transNodeDao;
	}

	public PositionDao() {
		super(Position.class);
	}

	public Position findPositionByTid(String tid) {

		TransNode transNode = transNodeDao.get(tid);
		int posCode = transNode.getPoscode();
		Position p = get(posCode);
		return p;
	}

	public Position findById(int id) {
		System.out.println("PositonDao: " + get(id).toString());
		return get(id);
	}

}
