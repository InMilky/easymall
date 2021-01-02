package easymall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import easymall.exception.UserLoginNoException;

@Controller
public class BaseController {
	@ModelAttribute
	public void isLogin(HttpSession session,HttpServletRequest request) throws UserLoginNoException{
		if(session.getAttribute("user")==null){
			throw new UserLoginNoException("没有登录，请先登录！");//没有登录，请先登录！
		}
		
	}
	
}
