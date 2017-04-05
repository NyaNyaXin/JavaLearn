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
		//在不设定级联关系的情况下，且1这一端的对象有n的对象在引用，不能直接删除1这一端的对象
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
		customer.setCustomerName("BB");
		Order order1 = new Order();
		order1.setOrderName("ORDER-3");
		Order order2 = new Order();
		order2.setOrderName("ORDER-4");

		// 设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		// 执行save操作:先插入Customer，再插入Order，3条insert
		// 先插入1的一端，再插入多的一端，只有insert语句
		// session.save(customer);
		// session.save(order1);
		// session.save(order2);

		// 先插入Order再插入Customer 3条insert，2条update
		// 先插入n的一端，再插入1的一端，会多出update语句
		// 因为再插入多的一端时，无法确定一的一端的外键值，所以只能等1的一端插入后，再额外发送update语句
		// 推荐先插入1的一端，后插入n的一端
		session.save(order1);
		session.save(order2);
		session.save(customer);
	}

}
