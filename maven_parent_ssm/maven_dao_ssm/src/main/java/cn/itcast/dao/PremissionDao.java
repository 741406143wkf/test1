package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.Premission;

public interface PremissionDao {
	
	@Select("select * from sys_permission")
	public List<Premission> findAllPermission();

	@Insert("insert into sys_permission values(common_sequence.nextval,#{permissionName},#{url})")
	public void savePremission(Premission premission);
	
	@Select("select * from sys_permission " + 
			"    where id in (select p.permissionid from sys_role_permission p where p.roleid = #{roleId} )")
	public List<Premission> findPremissionsById(Integer roleId);
}
