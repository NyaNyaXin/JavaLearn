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
		//�ڲ�ѯû�������ʵ�����ʱ��ʹ�õ����������Ӳ�ѯ��һ����ѯ����������󣬲��Ѿ����г�ʼ��
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getMgrName());
		System.out.println(manager.getDept().getDeptName());
	}
	@Test
	public void testGet(){
		//1.Ĭ������£��Թ�������ʹ��������
		//2.���Ի�����������쳣������
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
		
		//�趨������ϵ
		department.setMgr(manager);
		manager.setDept(department);
		
		//�������
		//�Ȳ�����һ���������ж����Update
		session.save(department);
		session.save(manager);
	}
	
}
