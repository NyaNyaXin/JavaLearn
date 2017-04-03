package com.cx.hibernate.helloworld;

import static org.junit.Assert.*;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		// 1.创建SessionFactory对象
		SessionFactory sessionFactory = null;

		// 1).创建Configuration对象：对应hibernate的基本配置信息和对象关系映射信息
		Configuration configuration = new Configuration().configure();

		// 4.0之前这样创建
		// sessionFactory = configuration.buildSessionFactory();
		// 2).创建一个ServiceRegistry对象：hibernate4.x新添加的对象
		// hibernate的任何配置和服务都需要在该对象中注册后才有效
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 2.创建一个Session对象
		Session session = sessionFactory.openSession();
		// 3.开启事务
		Transaction transaction =  session.beginTransaction();
		// 4.执行保存操作
		News news = new News("Java","Chen",new Date(new java.util.Date().getTime()));
		session.save(news);
//		News news2 = (News) session.get(News.class, 1);
//		System.out.println(news2);
		// 5.提交事务
		transaction.commit();
		// 6.关闭Session
		session.close();
		// 7.关闭SessionFactory对象
		sessionFactory.close();
	}

}
