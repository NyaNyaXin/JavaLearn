package com.cx.hibernate.entities.n21.both;

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
	public void testUpdate2(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrders().iterator().next().setOrderName("GGG");
	}
	@Test
	public void testUpdate(){
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
		
	}
	@Test
	public void testOne2Many(){
		//1.��n��һ�˵ļ���ʹ���ӳټ���
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		//2.���صĶ��һ�˵ļ�����hibernate���õļ�������
		//�����;����ӳټ��غʹ�Ŵ������Ĺ���
		System.out.println(customer.getOrders().getClass());
		
		//3.���ܻ��׳��������쳣
//		session.close();
//		System.out.println(customer.getOrders().size());
		
		//4.����Ҫʹ�ü�����Ԫ�ص�ʱ����г�ʼ��
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
		customer.setCustomerName("CC");
		Order order1 = new Order();
		order1.setOrderName("ORDER-5");
		Order order2 = new Order();
		order2.setOrderName("ORDER-6");

		// �趨������ϵ
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		

		// ִ��save����:�Ȳ���Customer���ٲ���Order��3��insert,2��update
		//��Ϊ1��һ�˺�n��һ�˶�ά��������ϵ�����Ի���updateshi
		//������1��һ�˵�set�ڵ�ָ��inverse=true��ʹ1��һ�˷���ά��������ϵ
		//�����趨set��inverse=true�������Ȳ���1��һ�ˣ��������һ��
		//�ô��ǲ�����update���
		 session.save(customer);
		 session.save(order1);
		 session.save(order2);
		//�Ȳ���order�ٲ���customer��3��insert��4��update
//		session.save(order1);
//		session.save(order2);
//		session.save(customer);
	}

}
