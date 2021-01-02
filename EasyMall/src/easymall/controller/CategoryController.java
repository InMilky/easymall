package easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Category;
import easymall.service.CategoryService;

@Controller("categoryController")
@RequestMapping("/adminCategory")
public class CategoryController {

	@Autowired 
	private CategoryService categoryService;
	
	@RequestMapping("/categoryLists")
	public String showcategory(Model model){
		List<Category> category = categoryService.showCategory();
		model.addAttribute("categorys", category);
		return "admin/category_lists";
	}
	
	@RequestMapping("/toaddCategory")
	public String toaddcategory(){
		return "admin/add_category";
	}
	
	@RequestMapping("/addCategory")
	public String addcategory(Category category,Model model){
		if(category.getName()=="" || category.getName()==null || category.getDescription()=="" || category.getDescription()==null){
			model.addAttribute("msg", "不能为空");
		}else{
			int n = categoryService.addCategory(category);
			if(n>0){
				model.addAttribute("msg", "添加成功");
			}else{
				model.addAttribute("msg", "添加失败");
			}
		}
		List<Category> categorys = categoryService.showCategory();
		model.addAttribute("categorys", categorys);
		return "admin/category_lists";
	}
	
	@RequestMapping("/toeditCategory")
	public String toeditCategory(String id,Model model){
		Category category = categoryService.oneCategory(id);
		model.addAttribute("category", category);
		return "admin/edit_category";
	}
	
	@RequestMapping("/updateCategory")
	public String updatecategory(Category category,Model model)
	{
		categoryService.updateCategory(category);
		List<Category> categorys = categoryService.showCategory();
		model.addAttribute("categorys", categorys);
		return "admin/category_lists";
	}
	
	@RequestMapping("/delCategory")
	public String delcategory(Integer categoryID,Model model){
		categoryService.delCategory(categoryID);
		return "redirect:/adminCategory/categoryLists";
	}
}
