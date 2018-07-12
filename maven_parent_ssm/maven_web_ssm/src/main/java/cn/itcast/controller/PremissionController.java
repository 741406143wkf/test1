package cn.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Premission;
import cn.itcast.service.PremissionService;

@Controller
@RequestMapping("/premission")
public class PremissionController {

	@Autowired
	private PremissionService premissioService;
	
	@RequestMapping("/findAllPremission")
	public String findAllPremission(Model model) {
		
		model.addAttribute("premissionList",premissioService.findAllPremission());
		return "premission/premissionList";
	}
	//跳转添加权限的页面
	@RequestMapping("/addPremissionUI")
	public String addPremissionUI() {
		
		
		return "premission/addPremission";
	}
	//保存数据
	@RequestMapping("/savePremission")
	public String savePremission(Premission premission) {
		
		premissioService.savePremission(premission);
		
		return "redirect:/premission/findAllPremission";
		
	}
}
