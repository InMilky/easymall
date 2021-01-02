package easymall.service;

import easymall.po.Admin;

public interface AdminService {

	public Admin adminLogin(Admin admin);
//	检查用户是否已被注册
	public Boolean checkAdminName(String username);
//	注册用户
	public int adminRegist(Admin admin);
}
