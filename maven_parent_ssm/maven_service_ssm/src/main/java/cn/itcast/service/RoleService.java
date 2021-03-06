package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Role;

public interface RoleService {
	
	public List<Role> findAllRole();

	public void saveRole(Role role);

	public Role findRoleById(Integer roleId);

	public void saveRolePremissions(Integer roleId, Integer[] ids);

}
