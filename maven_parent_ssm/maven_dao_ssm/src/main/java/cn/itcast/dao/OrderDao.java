package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.Order;
import cn.itcast.domain.Product;
/*
 * xml配置文件
 * association指定唯一属性
 * collection 指定属性的集合
 * select="查询的方法 cn.itcast.dao.findAllProductByOrderId"
 * */

public interface OrderDao {

	@Select("select * from orders")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="productId",property="product",javaType=Product.class,
		  one=@One(select="cn.itcast.dao.ProductDao.findProductById"))
	})
	public List<Order> findAllOrder();
}
