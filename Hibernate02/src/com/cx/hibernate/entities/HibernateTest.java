package com.cx.hibernate.entities;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	/**
	 * save()方法：
	 * 1.是一个临时对象变为持久化对象
	 * 2.为对象分配id
	 * 3.在flush缓存时会发送一条insert语句
	 * 4.在save()方法之前设置的id是无效的
	 * 5.持久化对象的id是不能被修改的！
	 * **/
	@Test
	public void testSave(){
		News news = new News();
		news.setTitle("CC");
		news.setAuthor("cc");
		news.setDate(new Date());
		//news.setId(100);
		System.out.println(news);
		session.save(news);
		System.out.println(news);
		//news.setId(101);
	}
	@Test
	public void testClear(){
		News news = (News) session.get(News.class, 1);
		session.clear();
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news);
	}
	/**
	 * refresh()：会强制发送SELECT语句，以使Session缓存中对象的状态和数据表中对应的记录保持一致！
	 * **/
	@Test
	public void testRefresh(){
		News news = (News) session.get(News.class,1);
		System.out.println(news);
		session.refresh(news);
		System.out.println(news);
	}
	/*
	 * flush:使数据表中的记录和Session缓存中的对象的状态保持一致，为了保持一致则可能会发送对应的SQL语句
	 * 1.在Transaction的commit()方法中：先调用Session的flush()方法，再提交事务
	 * 2.flush()可能会发送SQL语句，但不会提交事务
	 * 3.注意：在未提交事务或显式的调用Session.flush()方法之前，也有可能会进行flush()操作
	 * 1).执行HQL或QBC查询，会先进行flush()操纵,以得到数据表的最新的数据
	 * 2).若记录的id是由底层数据库使用自增的方式生成的，则在调用save方法时就会立即发送insert语句，因为save方法后，必须保证对象的id
	 * 是存在的！
	 * **/
	
	@Test
	public void testSessionFlush2(){
		News news = new News("Java","SUN",new Date());
		session.save(news);
	}
	@Test
	public void testSessionFlush(){
		 News news = (News) session.get(News.class, 1);
		 news.setAuthor("Oracle");
		 
//		 session.flush();
//		 System.out.println("flush");
		 
		 News news2 = (News) session.createCriteria(News.class).uniqueResult();
		 System.out.println(news2);
	}

	@Test
	public void testSessionCache() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);

		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);

		System.out.println(news == news2);
	}

}
