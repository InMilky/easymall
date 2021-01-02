package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.OrderItem;
import easymall.po.Orders;

@Repository("orderItemDao")
@Mapper
public interface OrderItemDao {
//	添加订单 商品信息
	public void addOrderItem(OrderItem orderItem);
//	显示订单 商品信息
	public List<OrderItem> orderitem(String order_id);
//	删除订单
	public void delorderitem(String id);
//	根据oid查找未发货的order项
	public List<OrderItem> unordersByoid();
	
}
