package cn.itcast.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
/*@Aspect 注解当前类为切面类
 * @Component 将切面送入容器
 * */
@Aspect
@Component
public class SysLogUtil {
	
	//实体类保存数据使用
	private SysLog sysLog;
	
	@Autowired
	private SysLogService sysLogService;
	
	//rquest的属性
	@Autowired
	HttpServletRequest request;
	
	//在方法执行之前记录信息
	//Joinpoint 连接点 当前访问的方法
	@Before("execution(* cn.itcast.controller.*.*(..))")
	public void beforeExecute(JoinPoint jp) {
		try {
	    sysLog = new SysLog();
		//ip地址的获取 HttpServletRequest
		sysLog.setIp(request.getRemoteAddr());
		//通过连接点获取访问的类
		Class target =  jp.getTarget().getClass();
		//获取当前类的名称
		String className= target.getSimpleName();
		//获取当前访问的方法名称
		String methodName = jp.getSignature().getName();
		//method为类名加上方法名
		sysLog.setMethod(className+methodName);
		//访问的用户名
		SecurityContext context = SecurityContextHolder.getContext();
		User user = (User) context.getAuthentication().getPrincipal();
		sysLog.setUsername(user.getUsername());
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	//在方法执行完成之后记录信息
	@After("execution(* cn.itcast.controller.*.*(..))")
	public void afterExecute() {
		//访问时间
	   sysLog.setVisitTime(new Date());
	   //存储到数据库
	   sysLogService.saveSysLog(sysLog);
	}
}
