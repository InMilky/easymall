package easymall.service;

import java.util.List;

import easymall.po.Category;

public interface CategoryService {
//	显示所有类别
	public List<Category> showCategory();
//	添加商品类别
	public int addCategory(Category category);
//	更新商品类别
	public int updateCategory(Category category);
//	删除商品类别
	public void delCategory(Integer categoryID);
//	根据id查找category
	public Category oneCategory(String id);
}
