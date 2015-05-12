package ts.daoImpl;

import java.util.List;

import ts.daoBase.BaseDao;
import ts.model.User;

public class UserDao extends BaseDao<User, Integer> {
	public UserDao() {
		super(User.class);
	}

	/**
	 * 判断登录
	 */
	public boolean checkLogin(String uname, String pwd) {

		List<User> user = findBy("uname", uname, uname, true);
		User getUser = user.get(0);
		if (pwd == getUser.getPassword()) {
			return true;
		} else {
			return false;
		}
	}
}
