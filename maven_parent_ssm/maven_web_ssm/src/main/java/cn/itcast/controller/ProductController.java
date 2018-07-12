package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.PageBean;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	/*日期传参格式化
	 * 1.新建类实现Convert接口
	 * 2.@DateFormat("")
	 * 3.自定义属性编辑器
	
	@InitBinder
	public void initBindStr(WebDataBinder initBinder) {
		//注册自定义的属性编辑器
		initBinder.registerCustomEditor(Date.class, new PropertiesEditor() {

			@Override
			public void setAsText(String dateStr) throws IllegalArgumentException {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					Date date = sdf.parse(dateStr);
					setValue(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
	} * */
	
	
	//产品的业务类
	@Autowired
	private ProductService productService;
	
	
	//查询所有的产品信息
	@RequestMapping("/findAllProduct")
	public String findAllProduct(Model model,
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="10")Integer pageSize) {
		//调用servie业务类得到分页的对象
		PageBean<Product> pageBean = productService.findAllProduct(pageNum,pageSize);
		//将得到的分页对象返回到页面显示
		model.addAttribute("pageBean",pageBean);
		
		
	 return "product/productList";
	}
	//添加产品的页面跳转
	@RequestMapping("/addPrductUI")
	public String addPrductUI() {
		
		return "product/productAdd";
	}
	
	//接受参数保存产品
	@RequestMapping("/productAdd")
	public String productAdd(Product product) {
		
		//保存数据
		productService.saveProduct(product);
		//返回查询list结果页面 
		//转发  重定向 forward  redirect
		return "forward:/product/findAllProduct";
		
	}
	//修改产品的页面跳转方法
	@RequestMapping("/updateProductUI")
	public String updateProductUI(Integer productId,Model model) {
		//查询出当前的产品信息用于页面回显
		Product product =productService.findProductById(productId);
		model.addAttribute("product",product);
		
		return "product/productEdit";
		
	}
	//接受产品信息根据id修改产品数据
	@RequestMapping("/updateProduct")
	public String updateProduct(Product product) {
		//修改数据
		productService.updateProduct(product);
		//修改数据后继续跳转列表页面
		return "redirect:/product/findAllProduct";
	}
	//删除产品
	@RequestMapping("/deleteProduct")
	public String deleteProduct(Integer productId) {
		
		productService.deleteProductById(productId);
		
		return "redirect:/product/findAllProduct";
		
	}
	

}
