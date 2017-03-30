package com.cx.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

//自定义的FactoryBean需要实现Spring提供的FactoryBean接口
public class CarFactoryBean implements FactoryBean{
	
	private String brand;

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	/**
	 * 返回bean的对象
	 * **/
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand,500000);
	}

	@Override
	/**
	 * 返回bean的类型
	 * **/
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	@Override
	/**
	 * 是否为单例的bean
	 * **/
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
