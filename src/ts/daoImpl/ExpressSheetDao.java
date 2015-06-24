package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ts.daoBase.BaseDao;
import ts.model.Customer;
import ts.model.ExpressSheet;

public class ExpressSheetDao extends BaseDao<ExpressSheet, String> {
	private RegionDao regionDao;

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public ExpressSheetDao() {
		super(ExpressSheet.class);
	}

	// 重写的get方法,将客户的区域字符串加入
	@Override
	public ExpressSheet get(String id) {
		// 父类（BaseDao）只是把es拿出来，没有查询其他属性
		ExpressSheet es = super.get(id);
		// 因为Es表里sender和receiver和客户表外键关系，所以可以关联查询
		if (es != null) {
			Customer ci = es.getReceiver();
			if (ci != null)
				// RegionString不是客户表的属性，是自定义的一个属性（@Transient不是一个类的永久性的状态）
				// 内容是通过客户的regioncode，查询到客户的region地址
				ci.setRegionString(regionDao.getRegionNameByID(ci
						.getRegioncode()));
			Customer cs = es.getSender();
			if (cs != null)
				cs.setRegionString(regionDao.getRegionNameByID(cs
						.getRegioncode()));
		}
		return es;
	}

	// 获得指定包裹ID的所有快件列表
	public List<ExpressSheet> getListInPackage(String pkg_id) {
		String sql = "{alias}.id in (select expresssheetid from distributecenter where packageid = '"
				+ pkg_id + "')";
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		list = findBy("id", true, Restrictions.sqlRestriction(sql));
		return list;
	}

	public static void main(String args[]) {

		org.springframework.context.ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ExpressSheetDao ed = (ExpressSheetDao) context
				.getBean("expressSheetDao");
		PackageDao pd = (PackageDao) context.getBean("packageDao");

		System.out.println(pd.get("1111"));
	}

}
