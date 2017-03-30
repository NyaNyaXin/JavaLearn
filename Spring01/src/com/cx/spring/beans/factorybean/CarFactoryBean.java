package com.cx.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

//�Զ����FactoryBean��Ҫʵ��Spring�ṩ��FactoryBean�ӿ�
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
	 * ����bean�Ķ���
	 * **/
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand,500000);
	}

	@Override
	/**
	 * ����bean������
	 * **/
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	@Override
	/**
	 * �Ƿ�Ϊ������bean
	 * **/
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
