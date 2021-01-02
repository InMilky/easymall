package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;

@Repository("categoryDao")
@Mapper
public interface CategoryDao {
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
