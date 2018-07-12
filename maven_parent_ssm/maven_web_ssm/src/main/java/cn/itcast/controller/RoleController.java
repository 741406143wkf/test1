package cn.itcast.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Premission;
import cn.itcast.domain.Role;
import cn.itcast.service.PremissionService;
import cn.itcast.service.RoleService;

@Controller
@RequestMapping("/sysRole")
//@RolesAllowed("ROLE_ADMIN")
//@Secured("ROLE_ADMIN")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PremissionService premissionService;
	//查询所有的角色
	@RequestMapping("/findAllRole")
	public String findAllRole(Model model) {
		
		model.addAttribute("roleList", roleService.findAllRole());
		
		return "role/roleList";
	}
	//添加角色页面跳转
	@RequestMapping("/addRoleUI")
	public String addRoleUI() {
		
		return "role/addRole";
		
	}
	
	//添加角色数据保存
	@RequestMapping("/addRole")
	public String addRoleUI(Role role) {
		
		roleService.saveRole(role);
		
		return "redirect:/sysRole/findAllRole";
		
	}
	//管理角色页面跳转
	@RequestMapping("/addRolePremissionUI")
	public String addRolePremissionUI(Integer roleId,Model model) {
		//1.查询当前的角色信息
		Role role = roleService.findRoleById(roleId);
		model.addAttribute("role", role);
		//2.当前角色的所有权限
		List<Premission> rolePremissioins = role.getPremissions();
		StringBuilder sb = new StringBuilder();
		if(rolePremissioins!=null&&rolePremissioins.size()>0) {
			for(Premission p:rolePremissioins) {
				sb.append(p.getPermissionName()+",");
			}
			model.addAttribute("premissionStr", sb.toString());
		}
		
		//3.数据库所有权限
		List<Premission> premissions  = premissionService.findAllPremission();
	    model.addAttribute("premissions",premissions);
		return "role/addRolePremission";
	}
	
	//根据传递的角色id和权限的数组id添加权限
	@RequestMapping("/addRolePermission")
	public String addRolePermission(Integer roleId,Integer [] ids) {
		//保存用户的角色
		roleService.saveRolePremissions(roleId,ids);
		return "redirect:/sysRole/findAllRole";
	}

}
