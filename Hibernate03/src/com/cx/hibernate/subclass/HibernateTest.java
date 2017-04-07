package com.cx.hibernate.subclass;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

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
	 * 查询：
	 * 1.查询父类记录，只需查询一张数据表
	 * 2.对于子类记录,也只需查询一张数据表
	 * 
	 * 缺点：
	 * 1.使用了辨别者列
	 * 2.子类独有的字段不能添加非空约束
	 * 3.若继承的层次比较深，则数据表的字段也会较多
	 * */
	@Test
	public void testQuery(){
		List<Person> persons = session.createQuery("FROM Person").list();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("FROM Student").list();
		System.out.println(students.size());
	}
	/*
	 * 插入操作：
	 * 1.对于子类对象只需把记录插入到一张数据表中
	 * 2.辨别者列由hibernate自动维护
	 * ***/
	@Test
	public void testSave(){
		Person person = new Person();
		person.setAge(11);
		person.setName("AA");
		session.save(person);
		Student student = new Student();
		student.setAge(11);
		student.setName("BB");
		student.setSchool("AAA");
		session.save(student);
		
	}
	
}
