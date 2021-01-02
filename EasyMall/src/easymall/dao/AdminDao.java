package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Admin;
import easymall.po.Products;

@Repository("adminDao")
@Mapper
public interface AdminDao {

	public Admin adminLogin(Admin admin);
//	检查用户是否已被注册
	public Admin checkAdminName(String username);
//	注册用户
	public int adminRegist(Admin admin);
}
