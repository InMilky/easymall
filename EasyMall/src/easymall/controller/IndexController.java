package easymall.controller;
//������Ŀtomcat�е�serlet-api��jar
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("indexController")
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/regist")
	public String register(){
		return "regist";
	}
	
	@RequestMapping("/adminlogin")
	public String adminlogin(){
		return "admin/login";
	}
	
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/adminRegist")
	public String adminRegister(){
		return "admin/regist";
	}
}
