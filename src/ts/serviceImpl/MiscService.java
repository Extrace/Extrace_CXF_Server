package ts.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ts.daoImpl.CustomerDao;
import ts.daoImpl.PositionDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransNodeDao;
import ts.daoImpl.UserDao;
import ts.model.CodeNamePair;
import ts.model.Customer;
import ts.model.Position;
import ts.model.Region;
import ts.model.TransNode;
import ts.model.User;
import ts.serviceInterface.IMiscService;

public class MiscService implements IMiscService {
	// TransNodeCatalog nodes; //自己做的缓存和重定向先不要了,用Hibernate缓存对付一下，以后加上去
	// RegionCatalog regions;
	private TransNodeDao transNodeDao;
	private RegionDao regionDao;
	private CustomerDao customerDao;
	private UserDao userDao;
	private PositionDao positionDao;

	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao dao) {
		this.transNodeDao = dao;
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao dao) {
		this.customerDao = dao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}

	public MiscService() {
		// nodes = new TransNodeCatalog();
		// nodes.Load();
		// regions = new RegionCatalog();
		// regions.Load();
	}

	@Override
	public List<Customer> getCustomerListByName(String name) {
		return customerDao.findByName(name);
	}

	@Override
	public List<Customer> getCustomerListByTelCode(String TelCode) {
		return customerDao.findByTelCode(TelCode);
	}

	@Override
	public Response getCustomerInfo(String id) {
		Customer cstm = customerDao.get(Integer.parseInt(id));
		return Response.ok(cstm).header("EntityClass", "Customer").build();
	}

	@Override
	public Response deleteCustomerInfo(int id) {
		customerDao.removeById(id);
		return Response.ok("Deleted").header("EntityClass", "D_Customer")
				.build();
	}

	@Override
	public Response saveCustomerInfo(Customer obj) {
		try {
			customerDao.save(obj);
			return Response.ok(obj).header("EntityClass", "R_Customer").build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public List<CodeNamePair> getProvinceList() {
		List<Region> listrg = regionDao.getProvinceList();
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMId(), rg.getProvince());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getCityList(String prv) {
		List<Region> listrg = regionDao.getCityList(prv);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMId(), rg.getCity());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getTownList(String city) {
		List<Region> listrg = regionDao.getTownList(city);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for (Region rg : listrg) {
			CodeNamePair cn = new CodeNamePair(rg.getORMId(), rg.getTown());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public String getRegionString(String code) {
		return regionDao.getRegionNameByID(code);
	}

	@Override
	public Region getRegion(String code) {
		return regionDao.getFullNameRegionByID(code);
	}

	@Override
	public void CreateWorkSession(int uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doLogOut(int uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void RefreshSessionList() {
		// TODO Auto-generated method stub
	}

	@Override
	public String doLogin(String uname, String pwd) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		if (userDao.checkLogin(uname, pwd)) {
			try {
				obj.put("username", uname);
				obj.put("status", true);
			} catch (JSONException e) {
			}
		} else {
			obj.put("error_msg", "用户名或密码错误");
			obj.put("status", false);
		}
		return obj.toString();
	}

	@Override
	public Response getUser(String uname) {
		System.out.println("*****start getUser****");
		User user = userDao.get(uname);
		System.out.println(user.toString());
		return Response.ok(user).header("EntityClass", "User").build();
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.findAllCustomer("id", true);
	}

	@Override
	public List<TransNode> getTransNodeList() {
		return null;
	}

	@Override
	public List<TransNode> getTransNodeListByName(String name) {

		return transNodeDao.findByName(name);
	}

	@Override
	public List<TransNode> getTransNodeListByTelCode(String TelCode) {

		return transNodeDao.findByTelCode(TelCode);
	}

	@Override
	public List<TransNode> getNodebyId(String id) {
		return transNodeDao.findById(id);
	}

	@Override
	public List<TransNode> getNode(String code) {

		return transNodeDao.findByRegionCode(code);
	}

	@Override
	public List<TransNode> getNodebyName(String name) {

		return transNodeDao.findByName(name);
	}

	@Override
	public List<TransNode> getNodesList(String regionCode, int type) {

		return null;
	}

	@Override
	public Response getPositionByTid(String tid) {
		// TODO Auto-generated method stub
		Position p = positionDao.findPositionByTid(tid);
		return Response.ok(p).header("EntityClass", "Position").build();
	}

}
