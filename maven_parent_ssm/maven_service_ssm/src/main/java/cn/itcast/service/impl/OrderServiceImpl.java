package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Order;
import cn.itcast.service.OrderService;
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Transactional(readOnly=true)
	@Override
	public PageInfo<Order> findAllOrder(Integer pageNum,Integer pageSize) {
		//查询dao获取所有的订单数据之前调用静态方法 开始分页
		PageHelper.startPage(pageNum, pageSize);
		//list实际已经是page对象 pageNum pageSize  resutList
		List<Order> list = orderDao.findAllOrder();
		//通过查询的结果集合返回pageInfo分页对象
		PageInfo<Order> pageInfo = new PageInfo<Order>(list);
		return pageInfo;
	}

}
