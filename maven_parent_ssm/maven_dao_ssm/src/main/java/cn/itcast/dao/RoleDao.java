package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.Role;

public interface RoleDao {
	
	//查询所有角色
	@Select("select * from  sys_role")
	public List<Role> findAllRole();

	@Insert("insert into sys_role values(common_sequence.nextval,#{roleName},#{roleDesc})")
	public void saveRole(Role role);
	
	//根据用户的id查询所属的角色
	@Select("select * from sys_role " + 
			"    where id in (select roleId from sys_user_role where userid=#{userId})")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="premissions",javaType=List.class,
		many=@Many(select="cn.itcast.dao.PremissionDao.findPremissionsById"))
	})
	public List<Role> findRolesById(Integer userId);
	
	//通过角色id查询信息并且查询对应的权限集合
	@Select("select * from sys_role where id = #{roleId}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="premissions",javaType=List.class,
		many=@Many(select="cn.itcast.dao.PremissionDao.findPremissionsById"))
	})
	public Role findRoleById(Integer roleId);
	
	@Delete("delete from sys_role_permission where roleid=#{roleId}")
	public void deleteRolePremissions(Integer roleId);

	@Insert("insert into sys_role_permission values(#{pId},#{roleId})")
	public void saveRolePermission(@Param("roleId")Integer roleId, @Param("pId")Integer pId);
}
