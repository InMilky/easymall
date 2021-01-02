package easymall.service;

import java.util.List;

import easymall.po.User;

public interface UserService {
//是否登陆成功
	public User login(User user);  
//	检查用户是否已被注册
	public Boolean checkUsername(String username);
//	注册用户
	public int regist(User user);
//	获取所有注册用户 
	public List<User> allUsers();
}
