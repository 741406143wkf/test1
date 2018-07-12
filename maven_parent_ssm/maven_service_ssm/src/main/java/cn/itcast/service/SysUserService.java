package cn.itcast.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.SysUser;

public interface SysUserService extends UserDetailsService {
	
	
	
	//查询所有用户
	public PageInfo<SysUser> findAllUser(Integer pageNum,Integer pageSize);

	//保存用户的数据
	public void saveUser(SysUser user);

	//通过id查询详情
	public SysUser findUserDetailById(Integer userId);
	//添加用户的角色
	public void addUserRoles(Integer userId, Integer[] roleIds);

}
