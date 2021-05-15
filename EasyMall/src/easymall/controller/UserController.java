package easymall.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HttpSessionMutexListener;

import com.sun.mail.util.MailSSLSocketFactory;

import easymall.po.User;
import easymall.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(User user,HttpSession session,Model model){
		User muser = userService.login(user);
		if(muser!=null){
			session.setAttribute("user", muser);
			return "redirect:/index.jsp";
		}else{
			model.addAttribute("message","用户名或密码错误!");
			return "login";
		}
		
	}
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		if(userService.checkUsername(username)){
			response.getWriter().print("用户名"+username+"已被注册!");
		}else
			response.getWriter().print("恭喜您,"+username+"可以使用!");	
	}
	
	@RequestMapping("/regist")
	public String regist(User user,String valistr,HttpSession session,Model model)
	{
		if(user.getUsername()==null || user.getUsername()==""){
			model.addAttribute("msg", "用户名不能为空!");
			return "regist";
		}
		if(user.getPassword()==null || user.getPassword()==""){
			model.addAttribute("msg", "密码不能为空!");
			return "regist";
		}
//		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())){
//			model.addAttribute("msg", "验证码不能为空");
//			return "regist";
//		}

		if(userService.regist(user) > 0 ){
			model.addAttribute("msg", "注册成功!");
			return "regist";
		}else{
			model.addAttribute("msg", "注册失败!");
			return "regist";
		}
	}

//	@RequestMapping("/yanzheng")
//	public void yanzheng(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String email = request.getParameter("email");
//		System.out.println(email);
//		Properties prop = new Properties();
//        // 开启debug调试，以便在控制台查看
//        prop.setProperty("mail.debug", "true");
//        // 设置邮件服务器主机名
//        prop.setProperty("mail.host", "smtp.qq.com");
//        // 发送服务器需要身份验证
//        prop.setProperty("mail.smtp.auth", "true");
//        // 发送邮件协议名称
//        prop.setProperty("mail.transport.protocol", "smtp");
//        // 开启SSL加密，否则会失败
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        prop.put("mail.smtp.ssl.enable", "true");
//        prop.put("mail.smtp.ssl.socketFactory", sf);
//        // 创建session
//        Session session = Session.getInstance(prop);
//        // 通过session得到transport对象
//        Transport ts = session.getTransport();
//        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
//        ts.connect("smtp.qq.com", "975961121", "mbkejmeuirvabcgd");
//        
//        
//        // 创建邮件
//    //  获取6为随机验证码
//        String[] letters = new String[] {
//                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
//                "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
//                "0","1","2","3","4","5","6","7","8","9"};
//        String stringBuilder ="";
//        for (int i = 0; i < 6; i++) {
//            stringBuilder = stringBuilder + letters[(int)Math.floor(Math.random()*letters.length)];
//        }
//    
//	    // 创建邮件对象
//	    MimeMessage message = new MimeMessage(session);
//	    // 指明邮件的发件人
//	    message.setFrom(new InternetAddress("975961121@qq.com"));
//	    // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
//	    message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//	    // 邮件的标题
//	    message.setSubject("【易买网】验证码");
//	    // 邮件的文本内容
//	    message.setContent("欢迎您注册【易买网】,账号注册验证码为:"+stringBuilder+",请勿回复此邮箱", "text/html;charset=UTF-8");
//        // 发送邮件
//        ts.sendMessage(message, message.getAllRecipients());
//        ts.close();
//        response.getWriter().print(stringBuilder);
//	}
}
