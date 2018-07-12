package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Product;
import cn.itcast.util.PageBean;

public interface ProductService {
	
	//查询所有的产品信息
	public PageBean<Product> findAllProduct(Integer pageNum, Integer pageSize);
	//保存产品信息
	public void saveProduct(Product product);
	//根据id查询产品的数据
	public Product findProductById(Integer productId);
	//修改产品
	public void updateProduct(Product product);
	//根据id删除产品
	public void deleteProductById(Integer productId);

}
