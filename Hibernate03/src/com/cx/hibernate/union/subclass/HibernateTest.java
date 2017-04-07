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
	 * ��ѯ��
	 * 1.��ѯ�����¼����Ѹ�����ӱ��¼���ܵ�һ��������ѯ�������Բ�
	 * 2.���������¼,ֻ���ѯһս���ݱ�
	 * 
	 * �ŵ㣺
	 * 1.����Ҫʹ�ñ������
	 * 2.������е��ֶ�����ӷǿ�Լ��
	 * ȱ��
	 * 1.���������ֶ�
	 * 2.�����¸�����ֶ������Ч�ʽϵ�
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
	 * ���������
	 * 1.�����������������Ҫ���뵽�������ݱ���
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
