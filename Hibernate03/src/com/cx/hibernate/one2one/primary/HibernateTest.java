package com.cx.hibernate.one2one.primary;

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
	public void testGet2(){
		//在查询没有外键的实体对象时，使用的是左外连接查询，一并查询出其关联对象，并已经进行初始化
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getMgrName());
		System.out.println(manager.getDept().getDeptName());
	}
	@Test
	public void testGet(){
		//1.默认情况下，对关联属性使用懒加载
		//2.所以会出现懒加载异常的问题
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDeptName());

		Manager manager = department.getMgr();
		System.out.println(manager.getMgrName());
	}
	@Test
	public void testSave(){
		Department department = new Department();
		department.setDeptName("DEPT-CC");
		
		Manager manager = new Manager();
		manager.setMgrName("MGR-CC");
		
		//设定关联关系
		department.setMgr(manager);
		manager.setDept(department);
		
		//保存操作
		//先插入哪一个都不会有多余的Update
		session.save(department);
		session.save(manager);
	}
	
}
