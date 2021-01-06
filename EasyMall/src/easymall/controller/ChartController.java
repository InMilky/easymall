package easymall.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Products;
import easymall.poi.ExportExcel;
import easymall.pojo.MyProducts2;
import easymall.service.ProductsService;

@Controller("chartController")
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private ProductsService productsService;
	

	@RequestMapping("/showsaleslist")
	public String showsales() {
		return "redirect:/adminProducts/saleLists";
	}
	
	@RequestMapping("/printtable")
	public void printtable(HttpServletResponse response) {
		ExportExcel ex=new ExportExcel();
		String[] headers= {"商品名称","售出数量"};
		List<Products> productsList=productsService.allproducts();
		List<MyProducts2> dataset=new ArrayList<MyProducts2>();
		for(Products p:productsList) {
			dataset.add(new MyProducts2(p.getName(),p.getSoldnum()));
		}
		try {
			OutputStream out = new FileOutputStream("D:/销售榜单.xls");
			ex.exportExcel(headers, dataset, out);
			out.close();
			System.out.println("excel导出成功");
			//����
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("<script>alert('已将报表保存至D盘!');window.location='showsaleslist'; </script>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("barPie")
	public String tobarPie() {
		return "admin/barAndpie";
	}
	
	@RequestMapping("/getPie")
	public void getPie(HttpServletResponse response) {
		//�ֻ�����
		int num1=productsService.getNumViaCategory(1);
		//���ðٻ�
		int num2=productsService.getNumViaCategory(2);
		//���õ���
		int num3=productsService.getNumViaCategory(3);
		//ͼ����־
		int num4=productsService.getNumViaCategory(4);
		//��װ����
		int num5=productsService.getNumViaCategory(5);
		//������Ʒ
		int num6=productsService.getNumViaCategory(6);
		//������Ʒ
		int num7=productsService.getNumViaCategory(7);
		List<Integer> list=new ArrayList<Integer>();
		list.add(num1);
		list.add(num2);
		list.add(num3);
		list.add(num4);
		list.add(num5);
		list.add(num6);
		list.add(num7);
		 
		try {
			response.getWriter().print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
