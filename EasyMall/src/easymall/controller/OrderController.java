package easymall.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.OrderItem;
import easymall.po.Orders;
import easymall.po.Products;
import easymall.po.User;
import easymall.pojo.MyCart;
import easymall.pojo.OrderInfo;
import easymall.service.CartService;
import easymall.service.OrderService;
import easymall.service.ProductsService;
import easymall.service.UserService;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController extends BaseController{

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductsService productsService ;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/order_add")
	public String order_add(String cartIds, Model model){
//		1.将购物车中所有选中商品的cartID组合起来的字符串拆分为数组
		String[] arrCartIds=cartIds.split(",");
		List<MyCart> carts = new ArrayList<MyCart>();
//		2.遍历数组，根据cart ID编号查找购物车，添加到carts中
		for(String cid:arrCartIds){
			Integer cartID = Integer.parseInt(cid);
			MyCart cart = cartService.findByCartID(cartID);
			carts.add(cart);
		}
		model.addAttribute("carts",carts);
		model.addAttribute("cartIds",cartIds);
		return("order_add");
	}
	
	@RequestMapping("/addOrder")
	public String addOrder(String receiverinfo, String cartIds,HttpSession session)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());   //0去掉
		Timestamp timestamp = Timestamp.valueOf(time);
		User user = (User)session.getAttribute("user");
		String orderId=UUID.randomUUID().toString();
		Orders myOrder = new Orders(orderId,null,receiverinfo,0,timestamp,user.getId());
		orderService.addOrder(cartIds,myOrder);
		return "forward:/order/showorder" ;
	}
	
	@RequestMapping("/showorder")
	public String showorder(HttpSession session, Model model){
//		1.获取当前登录用户
		User user = (User) session.getAttribute("user");
//		2.根据用户id查询该用户所有订单信息，查询orders表
		List<OrderInfo> orderInfoList = findOrderInfoByUserId(user.getId());
//		3.将该用户的所有订单信息的list集合存入request域中，转发到order_list.jsp中显示
		model.addAttribute("orderInfos",orderInfoList);
		return "order_list";
	}

	@RequestMapping("/findOrderInfoByUserId")
	private List<OrderInfo> findOrderInfoByUserId(Integer userId)
	{
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
//		1.根据用户id查询用户所有订单信息，查询orders表
		List<Orders> orderList = orderService.findOrderByUserId(userId);
//		2.遍历每一个订单，根据订单id查询当前订单中包含所有订单项信息
		for(Orders order:orderList){
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
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
	
	@RequestMapping("/delorder")
	public String delorder(String id,Model model)
	{
		orderService.delorder(id);
		return "redirect:/order/showorder";
	}
	
	@RequestMapping("/payorder")
	public String payorder(String id,Model model)
	{
		orderService.payorder(id);
		return "redirect:/order/showorder";
	}
	@RequestMapping("/confirmorder")
	public String confirmorder(String id,Model model)
	{
		orderService.confirmorder(id);
		return "redirect:/order/showorder";
	}

}
