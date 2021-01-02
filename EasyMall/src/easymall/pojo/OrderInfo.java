package easymall.pojo;

import java.util.Map;

import easymall.po.Orders;
import easymall.po.Products;

public class OrderInfo {
	//订单信息
	private Orders order;	
	//该订单中的所有订单项信息
	private Map<Products,Integer> map;
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Map<Products, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Products, Integer> map) {
		this.map = map;
	}
	
}
