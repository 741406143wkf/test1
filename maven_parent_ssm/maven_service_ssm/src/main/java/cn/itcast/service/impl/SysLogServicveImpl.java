package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SysLogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;

@Service
@Transactional
public class SysLogServicveImpl implements SysLogService {

	//引入dao存储数据
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public void saveSysLog(SysLog sysLog) {
		// TODO Auto-generated method stub
		sysLogDao.saveLog(sysLog);
	}

}
