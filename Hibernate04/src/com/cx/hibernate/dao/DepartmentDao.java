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
		// �ڲ���ȡSession����
		// ��ȡ�͵�ǰ�̰߳󶨵�Session����
		// 1.����Ҫ���ⲿ����Session����
		// 2.���Dao����Ҳ����ʹ��һ������
		Session session = HibernateUtils.getInstance().getSession();
		System.out.println(session.hashCode());
		session.save(department);
	}

	/**
	 * ����Ҫ����һ��session��������ζ����һ��(Service)��Ҫ��ȡ��Session����
	 * ��˵����һ����Ҫ��Hibernate��API������ϡ����Բ��Ƽ�ʹ�����ַ�ʽ
	 */
	public void save(Session session, Department department) {
		session.save(department);
	}
}
