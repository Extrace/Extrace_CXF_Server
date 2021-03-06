package ts.serviceInterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ts.model.ExpressSheet;
import ts.model.Package;
import ts.model.TransHistory;

@Path("/Domain")
// 业务操作
public interface IDomainService {
	// 快件操作访问接口=======================================================================
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getExpressList/{Property}/{Restrictions}/{Value}")
	public List<ExpressSheet> getExpressList(
			@PathParam("Property") String property,
			@PathParam("Restrictions") String restrictions,
			@PathParam("Value") String value);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getExpressListInPackage/PackageId/{PackageId}")
	public List<ExpressSheet> getExpressListInPackage(
			@PathParam("PackageId") String packageId);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getTransHistoryList/ExpId/{ExpressSheetId}")
	public List<TransHistory> getTransHistoryList(
			@PathParam("ExpressSheetId") String eid);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/UnpackExpressList/PackageId/{PackageId}/uid/{UId}")
	public Response UnpackExpressListInPackage(
			@PathParam("PackageId") String packageId, @PathParam("UId") int uid);

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getExpressSheet/{id}")
	public Response getExpressSheet(@PathParam("id") String id);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/newExpressSheet/id/{id}/uid/{uid}")
	public Response newExpressSheet(@PathParam("id") String id,
			@PathParam("uid") int uid);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/saveExpressSheet")
	public Response saveExpressSheet(ExpressSheet obj);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/receiveExpressSheetId/id/{id}/uid/{uid}")
	public Response ReceiveExpressSheetId(@PathParam("id") String id,
			@PathParam("uid") int uid);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/dispatchExpressSheetId/id/{id}/uid/{uid}")
	public Response DispatchExpressSheet(@PathParam("id") String id,
			@PathParam("uid") int uid);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/addExpressSheetId/id/{id}/pid/{pid}/uid/{uid}")
	public Response AddExpressSheet(@PathParam("id") String id,
			@PathParam("pid") String pid, @PathParam("uid") int uid);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/deliveryExpressSheetId/id/{id}/uid/{uid}")
	public Response DeliveryExpressSheetId(@PathParam("id") String id,
			@PathParam("uid") int uid);

	// 包裹操作访问接口=======================================================================
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getTransPackageList/{Property}/{Restrictions}/{Value}")
	public List<Package> getPackageList(@PathParam("Property") String property,
			@PathParam("Restrictions") String restrictions,
			@PathParam("Value") String value);

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getPackageListByUid/uid/{uid}")
	public List<Package> getPackageListByUid(@PathParam("uid") int uid);

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/getTransPackage/{id}")
	public Response getTransPackage(@PathParam("id") String id);

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/packTransPackage/pid/{pid}/uid/{uid}")
	public Response packTransPackage(@PathParam("pid") String id,
			@PathParam("uid") int uid);

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/receivePackagByUid/pid/{pid}/uid/{uid}")
	public Response receivePackageByUid(@PathParam("pid") String id,
			@PathParam("uid") int uid);

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/newTransPackage/id/{id}/uid/{uid}")
	public Response newTransPackage(@PathParam("id") String id,
			@PathParam("id") int uid);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveTransPackage")
	public Response savePackage(Package obj);

}
