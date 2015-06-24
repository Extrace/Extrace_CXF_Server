package ts.daoTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import ts.daoImpl.ExpressSheetDao;

public class PackageTest extends HibernateDaoSupport {

	@Test
	public void test() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ExpressSheetDao ed = (ExpressSheetDao) context
				.getBean("expresssheetDao");
		System.out.println("exp:" + ed.get("11111111"));

	}
}
