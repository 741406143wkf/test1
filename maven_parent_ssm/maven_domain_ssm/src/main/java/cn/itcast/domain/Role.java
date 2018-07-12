package cn.itcast.domain;

import java.util.List;

public class Role {
	
	private Long id;
	private String roleName;   //角色名
	private String roleDesc;   //角色的描述
	//定义集合 角色拥有的所有权限
	private List<Premission> premissions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public List<Premission> getPremissions() {
		return premissions;
	}
	public void setPremissions(List<Premission> premissions) {
		this.premissions = premissions;
	}
	
	
	
}
