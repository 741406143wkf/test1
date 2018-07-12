package cn.itcast.dao;

import org.apache.ibatis.annotations.Insert;

import cn.itcast.domain.SysLog;

public interface SysLogDao {
	
	//插入日志信息到日志表
	@Insert("insert into sys_log values(common_sequence.nextval,"
			+ "    #{visitTime},#{username},#{ip},#{method})")
	public void saveLog(SysLog sysLog) ;
}
