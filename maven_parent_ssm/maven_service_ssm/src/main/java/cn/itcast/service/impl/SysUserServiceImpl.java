package cn.itcast.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.SysUserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.SysUser;
import cn.itcast.service.SysUserService;
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passWordencoder;
	/*security权限框架验证逻辑
	 * 通过登陆的用户名查询数据中对应的用户信息
	 * 返回给框架
	 * 框架通过登陆的密码和数据得到的密码验证
	 * 框架配置的需要权限和数据库用户的权限
	 * 
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userDao.findUserByName(username);
		//当前数据库用户拥有的权限集合
		//真实开发应该通过当前的用户查询对应的权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//通过当前用户的id得到所有的角色列表添加
		List<Role> userRoles = roleDao.findRolesById(sysUser.getId().intValue());
		for(Role role:userRoles){
			//循环一次添加一个角色
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		//user对象 包含数据库的用户名和密码 还有拥有的权限拥有验证
		User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
		return user;
	}
	
	//查询所有的用户
	@Override
	public PageInfo<SysUser> findAllUser(Integer pageNum,Integer pageSize) {
		
		//1.调用静态方法
		PageHelper.startPage(pageNum, pageSize);
		//2.dao查询所有数据
		List<SysUser> list = userDao.findAllUser();
		
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		//3.分页的用户对象返回
		return pageInfo;
	}

	//保存用户
	@Override
	public void saveUser(SysUser user) {
		//1.获取到当前注册的用户密码
		String passWord  = user.getPassword();
		//2.通过加密的工具类加密 //3.加密后的字符串赋值给user保存
		user.setPassword(passWordencoder.encode(passWord));
		userDao.saveUser(user);
	}

	@Override
	public SysUser findUserDetailById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.findUserDetailByID(userId);
	}

	@Override
	public void addUserRoles(Integer userId, Integer[] roleIds) {
		//删除当前用户原始的角色
		userDao.deleteUserRoles(userId);
		//添加用户传递参数的角色
		if (roleIds!=null&&roleIds.length>0) {
			for(Integer roleId:roleIds) {
				userDao.saveUserRole(userId,roleId);
			}
		}
		
	}

	

}
