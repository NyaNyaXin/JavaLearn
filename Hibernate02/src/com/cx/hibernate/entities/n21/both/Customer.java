package com.cx.hibernate.entities.n21.both;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer customerId;
	private String customerName;
	
	/*
	 * 1.������������ʱ����ʹ�ýӿ����ͣ���Ϊhibernate�ڻ�ȡ��������ʱ�����ص�ʱhibernate���õļ������ͣ�������javaseһ����׼�ļ���ʵ��
	 * 2.��Ҫ�Ѽ��Ͻ��г�ʼ�������Է�ֹ������ָ���쳣
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
