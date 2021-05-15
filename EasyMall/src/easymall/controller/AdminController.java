package easymall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.po.Admin;
import easymall.po.Category;
import easymall.po.OrderItem;
import easymall.po.Orders;
import easymall.po.Products;
import easymall.po.User;
import easymall.pojo.MyProducts;
import easymall.pojo.OrderInfo;
import easymall.pojo.OrderLists;
import easymall.service.AdminService;
import easymall.service.OrderService;
import easymall.service.ProductsService;
import easymall.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;	
	@Autowired
	private OrderService orderService;	
	@Autowired
	private ProductsService productsService;
	@Autowired
	private UserService userService;	
	
	@RequestMapping("/adminlogin")
	public String adminLogin(Admin admin,HttpSession session,Model model){
		Admin uadmin = adminService.adminLogin(admin);
		if(uadmin!=null){
			session.setAttribute("admin", uadmin);
			return "redirect:/main.jsp";
		}else{
			model.addAttribute("message","用户名或密码错误!");
			return "admin/login";
		}
	}

	@RequestMapping(value="/checkAdmin",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		if(adminService.checkAdminName(username)){
			response.getWriter().print("用户名"+username+"已被注册!");
		}else
			response.getWriter().print("恭喜您,"+username+"可以使用!");	
	}
	
	@RequestMapping("/adminRegist")
	public String regist(Admin admin,String valistr,HttpSession session,Model model)
	{
		if(admin.getUsername()==null || admin.getUsername()==""){
			model.addAttribute("msg", "用户名不能为空!");
			return "admin/regist";
		}
		if(admin.getPassword()==null || admin.getPassword()==""){
			model.addAttribute("msg", "密码不能为空!");
			return "admin/regist";
		}
//		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())){
//			model.addAttribute("msg", "验证码不能为空");
//			return "admin/regist";
//		}

		if(adminService.adminRegist(admin) > 0 ){
			model.addAttribute("msg", "注册成功!");
			return "admin/regist";
		}else{
			model.addAttribute("msg", "注册失败!");
			return "admin/regist";
		}
	}
	
	@RequestMapping("/prodLists")
	public String prodlists(){
		return "admin/prod_lists";
	}
	
	@RequestMapping("/prodsInfo")
	public String prodsinfo(){
		return "admin/prods_info";
	}
	
	@RequestMapping("/orderLists")
	public String orderlists(Model model){
		List<Orders> orderlists = orderService.showorderlists();
		model.addAttribute("orderlists", orderlists);
			return "admin/order_lists";
	}
	
	
//	查找所有订单
	@RequestMapping("/findAllOrderInfos")
	public List<OrderInfo> findAllOrderInfos(Integer userId){
		List<OrderInfo> orderInfoLists = new ArrayList<OrderInfo>();
		List<Orders>  OrderLists = orderService.findAllOrder(userId);
//		2.遍历每一个订单，根据订单id查询当前订单中包含所有订单项信息
		for(Orders order:OrderLists){
//			根据用户order_id查询该订单号的所有订单项信息，查询orderitem表
			List<OrderItem> orderitems = orderService.orderitem(order.getId());
//			遍历每一个订单项，通过订单项获取商品信息和商品购买数量
			Map<Products,Integer> map = new HashMap<Products,Integer>();
			for(OrderItem orderItem:orderitems){
//				获取商品id，根据商品id查询商品信息，返回Product对象
				Products product = productsService.oneProduct(orderItem.getProduct_id());
//				将商品信息和购买数量存入map中
				map.put(product, orderItem.getBuynum());
			}
//			将订单信息和所有订单项存入OrderInfo中
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
//			讲一个完整的订单信息存入List集合中
			orderInfoLists.add(orderInfo);
		}
		return orderInfoLists;
	}
	
	@RequestMapping("/showorder")
	public String showorder(HttpSession session, Model model){
//		1.获取所有注册用户
		List<User> users = userService.allUsers();
		List<OrderLists> allOrderInfoLists = new ArrayList<OrderLists>();
		for(User user : users)
		{
			OrderLists orderLists = new OrderLists();
			List<OrderInfo> orderInfoList = findAllOrderInfos(user.getId());
			if(orderInfoList!=null){
				orderLists.setOrderInfo(orderInfoList);
				orderLists.setUname(user.getUsername());
				allOrderInfoLists.add(orderLists);
			}
		}
		model.addAttribute("allOrderInfos", allOrderInfoLists);
		return "admin/order_lists";
	}
	
	
//	根据paystate查找oid
	@RequestMapping("/findOrderInfoBypaystate")
	public List<OrderInfo> findOrderInfoBypaystate(Integer userId){
		List<OrderInfo> unorderInfoList = new ArrayList<OrderInfo>();
//		1.根据paystate=1查询所有用户所有未发货订单信息，查询orders表
		List<Orders>  unOrderLists = orderService.findOrderIDBypaystate(userId);
//		2.遍历每一个订单，根据订单id查询当前订单中包含所有订单项信息
		for(Orders unorder:unOrderLists){
//			根据用户order_id查询该订单号的所有订单项信息，查询orderitem表
			List<OrderItem> unorderitems = orderService.orderitem(unorder.getId());
//			遍历每一个订单项，通过订单项获取商品信息和商品购买数量
			Map<Products,Integer> map = new HashMap<Products,Integer>();
			for(OrderItem unorderItem:unorderitems){
//				获取商品id，根据商品id查询商品信息，返回Product对象
				Products product = productsService.oneProduct(unorderItem.getProduct_id());
//				将商品信息和购买数量存入map中
				map.put(product, unorderItem.getBuynum());
			}
//			将订单信息和所有订单项存入OrderInfo中
			OrderInfo unorderInfo = new OrderInfo();
			unorderInfo.setOrder(unorder);
			unorderInfo.setMap(map);
//			讲一个完整的订单信息存入List集合中
			unorderInfoList.add(unorderInfo);
		}
		return unorderInfoList;
	}
	
	@RequestMapping("/showunorder")
	public String showunorder(HttpSession session, Model model){
//		1.获取所有注册用户
		List<User> users = userService.allUsers();
		List<OrderLists> allunorderInfoList = new ArrayList<OrderLists>();
		for(User user : users){
//		1.根据paystate=1查询所有用户所有未发货订单信息，查询orders表
			OrderLists unorderLists = new OrderLists();
			List<OrderInfo> unorderInfoList = findOrderInfoBypaystate(user.getId());
			if(unorderInfoList!=null){
				unorderLists.setOrderInfo(unorderInfoList);
				unorderLists.setUname(user.getUsername());
				allunorderInfoList.add(unorderLists);
			}
		}
		model.addAttribute("allunorderInfos", allunorderInfoList);
		return "admin/unorder_lists";
	}
	
	@RequestMapping("/sendorder")
	public String sendorder(String orderID,Model model)
	{
		orderService.sendorder(orderID);
		return "redirect:/admin/showunorder";
	}

}
