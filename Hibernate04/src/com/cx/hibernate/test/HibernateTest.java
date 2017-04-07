package com.cx.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cx.hibernate.entities.Department;
import com.cx.hibernate.entities.Employee;

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
	public void testNamedQuery(){
		Query query =session.getNamedQuery("salaryEmps");
		List<Employee> employees = query.setFloat("minSal",0).setFloat("maxSal", 1000).list();
		System.out.println(employees.size());
	}
	
	@Test
	public void testPageQuery(){
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		int pageNum = 4;
		int pageSize = 2;
		List<Employee> emps = query.setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).list();
		
		System.out.println(emps);
	}
	@Test
	public void testHQLNamesParameter(){
		//1.创建Query对象
		//基于命名参数
		String hql = "FROM Employee e WHERE e.salary>:sql and e.email LIKE :email ORDER BY e.salary";
		Query query = session.createQuery(hql);
		//2.绑定参数
		query.setFloat("sql", 200).setString("email", "%c%");
		//3.执行查询
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}
	
	@Test
	public void testHQL(){
		//1.创建Query对象
		//基于位置的参数
		String hql = "FROM Employee e WHERE e.salary>? and e.email LIKE ? and e.dept=?";
		Query query = session.createQuery(hql);
		//2.绑定参数
		//Query对象调用setXxx方法，支持方法链的编程风格
		Department dept = new Department();
		dept.setId(1);
		query.setFloat(0, 200).setString(1, "%c%").setEntity(2, dept);
		//3.执行查询
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}
	
}
