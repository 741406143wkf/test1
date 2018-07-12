package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Premission;

public interface PremissionService {

	public List<Premission> findAllPremission();

	public void savePremission(Premission premission);
}	
