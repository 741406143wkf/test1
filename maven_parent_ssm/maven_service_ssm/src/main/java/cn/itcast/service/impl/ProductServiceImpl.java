package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.PageBean;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public PageBean<Product> findAllProduct(Integer pageNum,Integer pageSize) {
		//组装分页的对象返回给controller
		PageBean<Product> pb = new PageBean<Product>();
		pb.setPageNum(pageNum);
		pb.setPageSize(pageSize);
		//通过dao得到查询的totalCount总记录数
		Integer totalCount = productDao.findTotalCount();
		pb.setTotalCount(totalCount);
		//计算totalPage
		Integer totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		pb.setTotalPage(totalPage);
		//dao分页查询得到结果集合
		/*需求： 查询 2页 每页5条记录
		 * mySql select * from product limit 5 , 5  startIndex,pageSize
		 * oracle的分页语句  
		 * select * from ( select rownum r,product.* from product )  t where t.r>5 and t.r<11
		 * select * from ( select rownum r,product.* from product  where rownum<=10)  t where t.r>5
		 * 5表示 startIndex  11 endIndex
		 * startIndex =(pageNum-1)*pageSize
		 * endIndex(pageNum-1)*pageSize+pageSize
		 * */
		Integer startIndex = (pageNum-1)*pageSize;
		Integer endIndex = pageNum*pageSize;
		//赋值分页后的结果集合
		List<Product> list = productDao.findProductByPage(startIndex,endIndex);
 		pb.setList(list);
		return pb;
	}

	@Override
	public void saveProduct(Product product) {
		
		productDao.saveProduct(product);
	}

	@Override
	public Product findProductById(Integer productId) {
		// TODO Auto-generated method stub
		return productDao.findProductById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProductById(Integer productId) {
		productDao.deleteProduct(productId);
	}

}
