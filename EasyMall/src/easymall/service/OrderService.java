package easymall.service;

import java.util.List;

import easymall.po.OrderItem;
import easymall.po.Orders;


public interface OrderService {

	public void addOrder(String cartIds, Orders myOrder);

	public List<Orders> findOrderByUserId(Integer user_id);

	public List<OrderItem> orderitem(String order_id);

	public void delorder(String id);
//	支付订单
	public void payorder(String id);
//	根据paystate查找未发货的order
	public List<Orders> findOrderIDBypaystate(Integer user_id);
//	显示所有订单信息
	public List<Orders> findAllOrder(Integer userId);
	public List<Orders> showorderlists();
//	订单发货
	public void sendorder(String id);
//	确认收货
	public void confirmorder(String orderID);

}
