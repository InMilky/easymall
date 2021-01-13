package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;
import easymall.po.Orders;
import easymall.po.Products;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
//	查找商品类别
	public List<Category> allcategorys();
//	显示所有商品
	public List<Products> showAllProds();
//	根据条件查找商品
	public List<Products> prodlist(Map<String,Object> map);
//	根据pid查找单个商品
	public Products oneProduct(String pid);
//	根据分类查找商品
	public List<Products> proclass(Integer category);
//	寻找图片的路径
	public Object findByImgurl(String imgurl);
//	保存添加商品的内容
	public void save(Products products);
//	搜索商品
	public List<Products> searchprods(String name);
//	根据categoryID查找name
	public String showCNameByID(String pid);
//	根据soldnum排序输出prods
	public List<Products> topsales();
//	根据条件查找销售榜商品
	public List<Products> prodToplist(Map<String, Object> map);
//	查找所有未发货的订单
	public List<Products> allunorders(String oid);
//	删除商品
	public void delProd(String pid);
//	查找所有未发货的订单的商品信息
	public List<Products> unordersByOI();
//	更新商品属性
	public int updateProduct(Products products);
}
