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
		//在不设定级联关系的情况下，且1这一端的对象有n的对象在引用，不能直接删除1这一端的对象
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
		//1.对n的一端的集合使用延迟加载
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		//2.返回的多的一端的集合是hibernate内置的集合类型
		//该类型具有延迟加载和存放代理对象的功能
		System.out.println(customer.getOrders().getClass());
		
		//3.可能会抛出懒加载异常
//		session.close();
//		System.out.println(customer.getOrders().size());
		
		//4.在需要使用集合中元素的时候进行初始化
	}

	@Test
	public void testManyToOneGet() {
		//1.若查询多的一端的一个对象，则默认情况下，只查询了多的一端的对象，而没有查询关联的1的那一端的对象
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());
		
		System.out.println(order.getCustomer().getClass().getName());
		
		session.close();
		//2.再需要使用到关联的对象时才发送对应的sql语句
		Customer customer = order.getCustomer();
		System.out.println(customer.getCustomerName());
		//3.在查询customer对象时，由多的一端导航到一的一端时，
		//若此时session已经关闭，则默认情况下
		//会发生懒加载异常
		
		//4.获取Order对象时，默认情况下其关联的Customer对象是一个代理对象
	}

	@Test
	public void testManyToOneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("CC");
		Order order1 = new Order();
		order1.setOrderName("ORDER-5");
		Order order2 = new Order();
		order2.setOrderName("ORDER-6");

		// 设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		

		// 执行save操作:先插入Customer，再插入Order，3条insert,2条update
		//因为1的一端和n的一端都维护关联关系，所以会多出updateshi
		//可以再1的一端的set节点指定inverse=true来使1的一端放弃维护关联关系
		//建议设定set的inverse=true，建议先插入1的一端，后插入多的一端
		//好处是不会多出update语句
		 session.save(customer);
		 session.save(order1);
		 session.save(order2);
		//先插入order再插入customer，3条insert，4条update
//		session.save(order1);
//		session.save(order2);
//		session.save(customer);
	}

}
