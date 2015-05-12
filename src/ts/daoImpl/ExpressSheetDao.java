package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

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
		ExpressSheet es = super.get(id);
		Customer ci = es.getReceiver();
		if (ci != null)
			ci.setRegionString(regionDao.getRegionNameByID(ci.getRegioncode())); // 获取区域的名字字符串
		Customer cs = es.getSender();
		if (cs != null)
			cs.setRegionString(regionDao.getRegionNameByID(cs.getRegioncode())); // 获取区域的名字字符串
		return es;
	}

	// 获得指定包裹ID的所有快件列表
	public List<ExpressSheet> getListInPackage(String pkg_id) {
		String sql = "{alias}.ID in (select ExpressSheetID from TransPackageContent where TransPackageID = '"
				+ pkg_id + "')";
		List<ExpressSheet> list = new ArrayList<ExpressSheet>();
		list = findBy("ID", true, Restrictions.sqlRestriction(sql));
		return list;
	}
}
