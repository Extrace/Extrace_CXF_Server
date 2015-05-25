package ts.daoImpl;

import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.Customer;

public class CustomerDao extends BaseDao<Customer, Integer> {
	private RegionDao regionDao;

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public CustomerDao() {
		super(Customer.class);
	}

	public Customer get(int id) {
		Customer ci = super.get(id);
		ci.setRegionString(regionDao.getRegionNameByID(ci.getRegioncode())); // 获取区域的名字字符串
		return ci;
	}

	public List<Customer> findByName(String name) {
		return findLike("cname", name + "%", "telcode", true);
	}

	public List<Customer> findByTelCode(String telCode) {
		return findBy("telcode", telCode, "telcode", true);
	}

	public List<Customer> findAllCustomer(String orderby, boolean isAsc) {
		System.out.println("***start find all cutomers****");
		return getAll(orderby, isAsc);
	}
}
