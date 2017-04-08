package com.cx.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cx.hibernate.entities.Department;
import com.cx.hibernate.hibernate.HibernateUtils;

public class DepartmentDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Department department) {
		// 内部获取Session对象
		// 获取和当前线程绑定的Session对象
		// 1.不需要从外部传入Session对象
		// 2.多个Dao方法也可以使用一个事务
		Session session = HibernateUtils.getInstance().getSession();
		System.out.println(session.hashCode());
		session.save(department);
	}

	/**
	 * 若需要传入一个session对象，则意味着上一层(Service)需要获取到Session对象
	 * 这说明上一层需要和Hibernate的API紧密耦合。所以不推荐使用这种方式
	 */
	public void save(Session session, Department department) {
		session.save(department);
	}
}
