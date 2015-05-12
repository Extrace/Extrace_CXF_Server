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
		return findLike("name", name + "%", "telCode", true);
	}

	public List<Customer> findByTelCode(String telCode) {
		return findBy("telCode", telCode, "telCode", true);
	}
}
