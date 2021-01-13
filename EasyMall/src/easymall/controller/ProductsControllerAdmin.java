package easymall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.service.ProductsService;

@Controller("productsControllerAdmin")
@RequestMapping("/adminProducts")
public class ProductsControllerAdmin {

	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/addprod")
	public String addprod(Model model){
//		查找商品表中的所有商品类别
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("myproducts", new MyProducts());
		return "admin/add_prod";
	}
	
	@RequestMapping("/save")
	public String save(@Validated @ModelAttribute MyProducts myProducts,
			HttpServletRequest request,Model model){
		if(myProducts.getName()=="" || myProducts.getName()==null || myProducts.getPrice()==null
				|| myProducts.getPnum()==null || myProducts.getImgurl()==null || myProducts.getDescription()==null
				|| myProducts.getDescription()==""){
			model.addAttribute("msg", "添加失败，任何一项都不能为空！");
		}
		else{
			String msg = productsService.save(myProducts,request);
			model.addAttribute("msg", msg);
		}
		return "admin/add_prod";//redirect:/adminProducts/addprod
	}
	
	@RequestMapping("/showAllProds")
	public String showAllProds(Model model){
//		查找商品表中所有的商品
		List<Products> products = productsService.showAllProds();
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("products",products);
//		返回到/WEB-INF/jsp/admin/prods_info.jsp
		return "admin/prods_info";
	}
	
	@RequestMapping("/updateprod")
	public String updateprod(Model model){
//		更新商品属性
		List<Products> products = productsService.showAllProds();
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("products",products);
		return "admin/update_prod";
	}
	
	@RequestMapping("/editprod")
	public String editprod(String pid,Model model){
//		修改需要更新的商品属性
		List<Category> categorys = productsService.allcategorys();
		Products product = productsService.oneProduct(pid);
		model.addAttribute("categorys", categorys);
		model.addAttribute("products",product);
		return "admin/edit_prod";
	}
	
	@RequestMapping("/update")
	public String update(@Validated @ModelAttribute("products") Products products){
//		更新商品属性@ModelAttribute("products") Products product
		System.out.println("into-update");
//		productsService.updateProduct(product);
		return "redirect:/adminProducts/updateprod";
//		return "admin/prods_info";
	}
	
	
	@RequestMapping("/delprod")
	public String delprod(String pid,Model model){
//		删除商品
		productsService.delProd(pid);
		List<Products> products = productsService.showAllProds();
		model.addAttribute("products",products);
		return "admin/del_prod";
	}
	
	@RequestMapping("/saleLists")
	public String salelists(Model model){
		List<Products> prodtops = productsService.topsales();
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("prodtops", prodtops);
		return "admin/sale_lists";
	}

	
}
