package ts.daoImpl;

import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.TransNode;

public class TransNodeDao extends BaseDao<TransNode, String> {

	private PositionDao positionDao;

	public TransNodeDao() {
		super(TransNode.class);
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public List<TransNode> findByRegionCode(String region_code) {
		List<TransNode> listNode = findLike("regioncode", region_code + "%",
				"id", true);
		for (TransNode node : listNode) {
			node.setPosition(positionDao.get(node.getPoscode()));
			System.out.println("node: " + node.toString());
			System.out.println("nodePosition: "
					+ positionDao.findById(node.getPoscode()) + "   Poscode:"
					+ node.getPoscode());

		}
		return listNode;
	}

	public List<TransNode> findById(String id) {

		List<TransNode> listNode = findLike("id", id + "%", "id", true);
		for (TransNode node : listNode) {
			node.setPosition(positionDao.get(node.getPoscode()));
		}
		return listNode;
	}

	public List<TransNode> findByName(String name) {

		List<TransNode> listNode = findLike("nodename", name + "%", "id", true);
		for (TransNode node : listNode) {
			node.setPosition(positionDao.get(node.getPoscode()));
		}
		return listNode;

	}

	public List<TransNode> findByTelCode(String telcode) {

		return null;

	}

}
