package cn.itcast.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.ProductDao;

public class DaoTest {
	
	@Test
	public void test() {
		
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
	    ProductDao dao = ac.getBean(ProductDao.class);
	    System.out.println(dao.findAllProduct());
	}
}
