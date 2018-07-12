package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.SysUser;

public interface SysUserDao {
	
	//通过用户名查询数据的用户信息
	@Select("select * from sys_user where username=#{userName}")
	public SysUser findUserByName(String userName);
	
	//查询所有用户的列表
	@Select("select * from sys_user")
	public List<SysUser> findAllUser();
	//插入数据保存
	@Insert("insert into sys_user values(common_sequence.nextval,"
			+ "#{username},#{email},#{password},#{phoneNum},#{status})")
	public void saveUser(SysUser user);

	@Select("select * from sys_user where id = #{userId}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="roles",javaType=List.class,
		 many=@Many(select="cn.itcast.dao.RoleDao.findRolesById"))
	})
	public SysUser findUserDetailByID(Integer userId);
	//根据用户的id查询用户的角色
	@Select("delete from sys_user_role where userid = #{userId}")
	public void deleteUserRoles(Integer userId);
	//根据用户id和角色id插入中间表数据
	@Insert("insert into sys_user_role values(#{userId},#{roleId})")
	public void saveUserRole(@Param("userId")Integer userId, @Param("roleId")Integer roleId);

}
