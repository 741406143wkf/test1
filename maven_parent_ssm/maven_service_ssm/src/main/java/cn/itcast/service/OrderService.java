package cn.itcast.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Order;

public interface OrderService {

	public PageInfo<Order> findAllOrder(Integer pageNum, Integer pageSize);
}
