package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.itcast.domain.Product;
/*接口+注解
 * 
 * */

public interface ProductDao {

	@Select("select * from product")
	public List<Product> findAllProduct();

	@Insert("insert into product values(common_sequence.nextval,"
			+ "#{productNum},#{productName},#{cityName},"
			+ "#{departureTime},#{productPrice},#{productDesc},"
			+ "#{productStatus})")
	public void saveProduct(Product product);
	
	
	@Select("select * from product where id = #{productId}")
	public Product findProductById(Integer productId);
	
	@Update("update product set productNum=#{productNum}, productName=#{productName},cityName=#{cityName},"
			+ "departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc}"
			+ ",productStatus=#{productStatus} where id = #{id}")
	public void updateProduct(Product product);

	@Delete("delete from product where id = #{productId}")
	public void deleteProduct(Integer productId);
	//总计所有表中记录数用于分页
	@Select("select count(1) from product")
	public Integer findTotalCount();
	//通过传递的起始位置 查询分页的数据
	@Select("select * from "
			+ "( select rownum r,product.* from product  where rownum<=#{endIndex})  t where t.r>#{startIndex}")
	public List<Product> findProductByPage(@Param("startIndex")Integer startIndex, @Param("endIndex")Integer endIndex);
	
	
}
