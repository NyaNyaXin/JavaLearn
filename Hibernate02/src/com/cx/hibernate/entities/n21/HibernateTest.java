package com.cx.hibernate.entities.n21;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.operations.Or;

public class HibernateTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void testDelete(){
		//�ڲ��趨������ϵ������£���1��һ�˵Ķ�����n�Ķ��������ã�����ֱ��ɾ��1��һ�˵Ķ���
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);
	}
	@Test
	public void testUpdate(){
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
		
	}

	@Test
	public void testManyToOneGet() {
		//1.����ѯ���һ�˵�һ��������Ĭ������£�ֻ��ѯ�˶��һ�˵Ķ��󣬶�û�в�ѯ������1����һ�˵Ķ���
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());
		
		System.out.println(order.getCustomer().getClass().getName());
		
		session.close();
		//2.����Ҫʹ�õ������Ķ���ʱ�ŷ��Ͷ�Ӧ��sql���
		Customer customer = order.getCustomer();
		System.out.println(customer.getCustomerName());
		//3.�ڲ�ѯcustomer����ʱ���ɶ��һ�˵�����һ��һ��ʱ��
		//����ʱsession�Ѿ��رգ���Ĭ�������
		//�ᷢ���������쳣
		
		//4.��ȡOrder����ʱ��Ĭ��������������Customer������һ���������
	}

	@Test
	public void testManyToOneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("BB");
		Order order1 = new Order();
		order1.setOrderName("ORDER-3");
		Order order2 = new Order();
		order2.setOrderName("ORDER-4");

		// �趨������ϵ
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		// ִ��save����:�Ȳ���Customer���ٲ���Order��3��insert
		// �Ȳ���1��һ�ˣ��ٲ�����һ�ˣ�ֻ��insert���
		// session.save(customer);
		// session.save(order1);
		// session.save(order2);

		// �Ȳ���Order�ٲ���Customer 3��insert��2��update
		// �Ȳ���n��һ�ˣ��ٲ���1��һ�ˣ�����update���
		// ��Ϊ�ٲ�����һ��ʱ���޷�ȷ��һ��һ�˵����ֵ������ֻ�ܵ�1��һ�˲�����ٶ��ⷢ��update���
		// �Ƽ��Ȳ���1��һ�ˣ������n��һ��
		session.save(order1);
		session.save(order2);
		session.save(customer);
	}

}
