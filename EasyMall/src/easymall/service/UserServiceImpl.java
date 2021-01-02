package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.UserDao;
import easymall.po.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public Boolean checkUsername(String username) {
		User user = userDao.checkUsername(username);
		if(user != null){
			return true;
		}else
			return false;
	}

	@Override
	public int regist(User user) {
		int n = userDao.regist(user);
		return n;
	}

	@Override
	public List<User> allUsers() {
		return userDao.allUsers();
	}

}
