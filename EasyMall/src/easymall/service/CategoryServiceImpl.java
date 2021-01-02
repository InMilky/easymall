package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.CategoryDao;
import easymall.po.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> showCategory() {
		return categoryDao.showCategory();
	}
	
	@Override
	public int addCategory(Category category) {

		return categoryDao.addCategory(category);
	}
	
	@Override
	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	public void delCategory(Integer categoryID) {
		categoryDao.delCategory(categoryID);
	}

	@Override
	public Category oneCategory(String id) {
		return categoryDao.oneCategory(id);
	}




}
