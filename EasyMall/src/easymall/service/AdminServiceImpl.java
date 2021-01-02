package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.AdminDao;
import easymall.po.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin adminLogin(Admin admin) {
		return adminDao.adminLogin(admin);
	}

	@Override
	public Boolean checkAdminName(String username) {
		Admin admin = adminDao.checkAdminName(username);
		if(admin != null){
			return true;
		}else
			return false;
	}

	@Override
	public int adminRegist(Admin admin) {
		int n = adminDao.adminRegist(admin);
		return n;
	}

}
