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

		System.out.println("uname" + uname);
		List<User> user = findBy("uname", uname, "uname", true);
		User getUser = user.get(0);
		System.out.println(getUser.toString());
		if (pwd.equals(getUser.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取User
	 * 
	 * @param uname
	 * @return
	 */
	public User get(String uname) {
		List<User> user = findBy("uname", uname, "uname", true);
		User getUser = user.get(0);
		return getUser;
	}
}
