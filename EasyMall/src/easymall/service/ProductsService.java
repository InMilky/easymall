package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.po.Category;
import easymall.po.Orders;
import easymall.po.Products;
import easymall.pojo.MyProducts;

public interface ProductsService {

//	查找商品类别
	public List<Category> allcategorys();
//	显示所有商品
	public List<Products> showAllProds();
//	根据条件查找商品
	public List<Products> prodlist(Map<String,Object> map);
//	根据pid查找单个商品
	public Products oneProduct(String pid);
//	根据分类查找商品
	public List<Products> proclass(Integer proclass);
//	添加商品
	public String save(MyProducts myProducts, HttpServletRequest request);
//	搜索商品
	public List<Products> searchprods(String name);
//	根据soldnum排序输出prods
	public List<Products> topsales();
//	根据条件查找销售榜商品
	public List<Products> prodToplist(Map<String, Object> map);
//	查找所有未发货的订单所包含的商品
	public List<Products> allunorders(String oid);
//	删除商品
	public void delProd(String pid);
//	查找所有未发货的订单的商品信息
	public List<Products> unordersByOI();
//	更新商品属性
	public int updateProduct(Products products);

	//查找所有商品
	public List<Products> allproducts();
	//获取某一类的商品的数量
	public Integer getNumViaCategory(Integer category);
}
