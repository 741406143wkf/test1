package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.RoleDao;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAllRole() {
		
		return roleDao.findAllRole();
	}

	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.saveRole(role);
	}
	
	//通过角色id查询角色的信息
	@Override
	public Role findRoleById(Integer roleId) {
	
		return roleDao.findRoleById(roleId);
	}

	
	@Override
	public void saveRolePremissions(Integer roleId, Integer[] ids) {
		//先通过当前的roleid 删除原有的权限
		roleDao.deleteRolePremissions(roleId);
		//根据传递的权限id数组 插入数据
		if(ids!=null&&ids.length>0) {
			for(Integer pId:ids) {
				roleDao.saveRolePermission(roleId,pId);
			}
		}
	}

}
