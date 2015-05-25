package ts.serviceInterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import ts.model.CodeNamePair;
import ts.model.Customer;
import ts.model.Region;
import ts.model.TransNode;

@Path("/Misc")
public interface IMiscService {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getUser")
	public Response getUser(@QueryParam("uname") String uname);

	/**
	 * 获取转运结点ById
	 * 
	 * @param code
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getNode/{NodeCode}")
	public TransNode getNode(@PathParam("NodeCode") String code);

	/**
	 * 获取转运结点列表
	 * 
	 * @param regionCode
	 * @param type
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getNodesList/{RegionCode}/{Type}")
	public List<TransNode> getNodesList(
			@PathParam("RegionCode") String regionCode,
			@PathParam("Type") int type);

	// 客户信息操作接口===============================================================================================

	/**
	 * 获取客户信息列表ByName
	 * 
	 * @param name
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerListByName/{name}")
	public List<Customer> getCustomerListByName(@PathParam("name") String name);

	/**
	 * 获取客户信息列表ByTelCode
	 * 
	 * @param TelCode
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerListByTelCode/{TelCode}")
	public List<Customer> getCustomerListByTelCode(
			@PathParam("TelCode") String TelCode);

	/**
	 * 获取客户信息列表ById
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerInfo/{id}")
	public Response getCustomerInfo(@PathParam("id") String id);

	/**
	 * 获取所有客户列表
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCustomerList")
	public List<Customer> getAllCustomer();

	/**
	 * 删除客户信息ById
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/deleteCustomerInfo/{id}")
	public Response deleteCustomerInfo(@PathParam("id") int id);

	/**
	 * 保存客户信息
	 * 
	 * @param obj
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveCustomerInfo")
	public Response saveCustomerInfo(Customer obj);

	// 省、市、城镇信息操作接口===============================================================================================

	/**
	 * 获取省份列表
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getProvinceList")
	public List<CodeNamePair> getProvinceList();

	/**
	 * 获取城市信息列表
	 * 
	 * @param prv
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getCityList/{prv}")
	public List<CodeNamePair> getCityList(@PathParam("prv") String prv);

	/**
	 * 获取乡镇信息列表
	 * 
	 * @param city
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getTownList/{city}")
	public List<CodeNamePair> getTownList(@PathParam("city") String city);

	/**
	 * 获取区域信息String ById
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("/getRegionString/{id}")
	public String getRegionString(@PathParam("id") String id);

	/**
	 * 获取区域信息对象ById
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getRegion/{id}")
	public Region getRegion(@PathParam("id") String id);

	// 登陆注册接口===============================================================================================
	public void CreateWorkSession(int uid);

	/**
	 * 登陆
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws JSONException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/doLogin")
	public String doLogin(@QueryParam("uname") String uname,
			@QueryParam("pwd") String pwd) throws JSONException;

	/**
	 * 登出
	 * 
	 * @param uid
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/doLogOut/{uid}")
	public void doLogOut(@PathParam("uid") int uid);

	public void RefreshSessionList();
}
