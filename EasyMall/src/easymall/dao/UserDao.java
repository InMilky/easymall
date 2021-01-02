package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Repository("userDao")
@Mapper
public interface UserDao {

	public User login(User user);
//	检查用户是否已被注册
	public User checkUsername(String username);
//	注册用户
	public int regist(User user);
//	获取所有注册用户 
	public List<User> allUsers();
}
