package com.cx.hibernate.union.subclass;

import java.util.List;

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
	
	@Test
	public void testUpdate(){
		String hql="UPDATE Person p SET p.age = 20";
		session.createQuery(hql).executeUpdate();
	}
	/**
	 * 查询：
	 * 1.查询父类记录，需把附表和子表记录汇总到一起在做查询，性能稍差
	 * 2.对于子类记录,只需查询一战数据表
	 * 
	 * 优点：
	 * 1.不需要使用辨别者列
	 * 2.子类独有的字段能添加非空约束
	 * 缺点
	 * 1.存在冗余字段
	 * 2.若更新父表的字段则更新效率较低
	 * 
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
	 * 1.对于子类对象至少需要插入到两张数据表中
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
