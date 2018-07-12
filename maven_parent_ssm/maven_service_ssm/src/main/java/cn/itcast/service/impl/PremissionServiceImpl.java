package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.PremissionDao;
import cn.itcast.domain.Premission;
import cn.itcast.service.PremissionService;
@Service
@Transactional
public class PremissionServiceImpl implements PremissionService {

	@Autowired
	private PremissionDao premissionDao;
	
	@Override
	public List<Premission> findAllPremission() {
		// TODO Auto-generated method stub
		return premissionDao.findAllPermission();
	}

	@Override
	public void savePremission(Premission premission) {
		
		premissionDao.savePremission(premission);
	}

}
