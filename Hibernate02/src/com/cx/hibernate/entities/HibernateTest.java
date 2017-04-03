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
	/*
	 * flush:ʹ���ݱ��еļ�¼��Session�����еĶ����״̬����һ�£�Ϊ�˱���һ������ܻᷢ�Ͷ�Ӧ��SQL���
	 * 1.��Transaction��commit()�����У��ȵ���Session��flush()���������ύ����
	 * 2.flush()���ܻᷢ��SQL��䣬�������ύ����
	 * 3.ע�⣺��δ�ύ�������ʽ�ĵ���Session.flush()����֮ǰ��Ҳ�п��ܻ����flush()����
	 * 1).ִ��HQL��QBC��ѯ�����Ƚ���flush()����,�Եõ����ݱ�����µ�����
	 * 2).����¼��id���ɵײ����ݿ�ʹ�������ķ�ʽ���ɵģ����ڵ���save����ʱ�ͻ���������insert��䣬��Ϊsave�����󣬱��뱣֤�����id
	 * �Ǵ��ڵģ�
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
