package cn.itcast.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Role;
import cn.itcast.domain.SysUser;
import cn.itcast.service.RoleService;
import cn.itcast.service.SysUserService;

@Controller
@RequestMapping("/sysUser")
@RolesAllowed("ROLE_ADMIN")
public class SysUserController {
	
	//引入用户的业务类
	@Autowired
	private SysUserService userService;
	//引入角色的业务类
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/findAllUser")
	public String findAllUser(@RequestParam(defaultValue="1")Integer pageNum,
			                  @RequestParam(defaultValue="5")Integer pageSize,
			                  Model model) {
		/*
		//获取当前springSecurity框架中的user信息
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User) context.getAuthentication().getPrincipal();
		System.out.println("当前登陆的用户名为"+user.getUsername());
		*/
		PageInfo<SysUser> pi = userService.findAllUser(pageNum, pageSize);
		model.addAttribute("pageInfo", pi);
		return "user/userList";
	}
	//跳转添加用户的方法
	@RequestMapping("/addUserUI")
	public String addUserUI() {
		
		return "user/addUser";
	}
	//接受用户的数据保存用户
	@RequestMapping("/saveUser")
	public String addUserUI(SysUser user) {
		
		userService.saveUser(user);
		
		return "redirect:/sysUser/findAllUser";
	}
	//通过当前用户的id查询用户的详情信息
	@RequestMapping("/findUserDetail")
	public String findUserDetail(Integer userId,Model model) {
		
		SysUser sysUser = userService.findUserDetailById(userId);
		
		model.addAttribute("user", sysUser);
		
		return "user/userDetail";
	}
	//管理角色的页面跳转
	@RequestMapping("/addUserRoleUI")
	public String addUserRoleUI(Integer userId,Model model) {
		
		//通过id查询得到用户的信息
		SysUser user = userService.findUserDetailById(userId);
		model.addAttribute("user", user);
		//得到用户的角色信息
		List<Role> userRoles = user.getRoles();
		//拼接所有用户的角色名称 ROLE_ADMIN ROLE_USER
		StringBuilder sb = new StringBuilder();
		if(userRoles!=null&&userRoles.size()>0) {
			for(Role role:userRoles) {
				sb.append(role.getRoleName()+",");
			}
			
			String roleStr =sb.toString();
			model.addAttribute("roleStr",roleStr);
		}
		//所有的角色
		List<Role> roles = roleService.findAllRole();
		
		model.addAttribute("roles", roles);
		
		return "user/addUserRole";
	}
	
	//根据传递的参数 保存用户的角色
	@RequestMapping("/addUserRole")
	public String addUserRole(Integer userId ,Integer [] roleIds) {
		
		//根据传递的角色id 保存对应的用户角色
		userService.addUserRoles(userId,roleIds);
		
		return "redirect:/sysUser/findAllUser";
	}

}
