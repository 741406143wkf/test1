package cn.itcast.domain;

import java.util.Date;

import cn.itcast.util.DateUtil;

public class Order {
		
	private Long id;
	private String orderNum;
	private Date orderTime;
	private String orderTimeStr;
	private Integer peopleCount;
	private String orderDesc;
	private Integer payType;
	private Integer orderStatus;
	//关联的产品属性
	private Product product;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", orderTime=" + orderTime + ", peopleCount="
				+ peopleCount + ", orderDesc=" + orderDesc + ", payType=" + payType + ", orderStatus=" + orderStatus
				+ ", product=" + product + "]";
	}
	public String getOrderTimeStr() {
		return DateUtil.formatDateToStr(this.orderTime);
	}
	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}
	
	
	
}
