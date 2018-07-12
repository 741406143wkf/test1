package cn.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Order;
import cn.itcast.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	//order业务类属性
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/findAllOrder")
	public String findAllOrder(Model model,
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="5") Integer pageSize) {
		//得到分页使用的PageInfo对象
		PageInfo<Order> pageInfo= orderService.findAllOrder(pageNum,pageSize);
		model.addAttribute("pageInfo",pageInfo);
		
		return "order/orderList";
	}
	
}
