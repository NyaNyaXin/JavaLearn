package com.cx.hibernate.entities.n21.both;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer customerId;
	private String customerName;
	
	/*
	 * 1.声明集合类型时，需使用接口类型，因为hibernate在获取集合类型时，返回的时hibernate内置的集合类型，而不是javase一个标准的集合实现
	 * 2.需要把集合进行初始化，可以防止发生空指针异常
	 * ***/
	private Set<Order> orders = new HashSet<>();
	
	
	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
