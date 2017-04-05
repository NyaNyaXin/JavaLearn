package com.cx.hibernate.entities;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
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
	public void testComponent(){
		Worker worker = new Worker();
		Pay pay = new Pay();
		pay.setMonthlyPay(1000);
		pay.setYearPay(80000);
		pay.setVocationWithPay(5);
		worker.setName("ABCD");
		worker.setPay(pay);
		session.save(worker);
	}
	@Test
	public void testBlob() throws Exception{
//		News news = new News();
//		news.setAuthor("cc");
//		news.setContent("CONTENT");
//		news.setDate(new Date());
//		news.setDesc("DESC");
//		news.setTitle("CC");
//		InputStream stream = new FileInputStream("img0_3840x2160.jpg");
//		Blob image= Hibernate.getLobCreator(session).createBlob(stream, stream.available());
//		
//		news.setImage(image);
//		session.save(news);
		
		News news = (News) session.get(News.class, 1);
		Blob image = news.getImage();
		InputStream in = image.getBinaryStream();
		System.out.println(in.available());
	}
	@Test
	public void testPropertyUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setTitle("AAA");
		System.out.println(news.getDesc());
		System.out.println(news.getDate().getClass());
	}
	@Test
	public void testIdGenerator() throws InterruptedException{
		News news = new News("AA","aa",new Date());
		session.save(news);
		
		//Thread.sleep(5000);
	}
	
	@Test
	public void testDynamicUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setAuthor("ABCD");
	}
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
				//���ô洢���̡�ת��JDBC
			}
		});
	}
	/**
	 * evict:��session�����а�ָ���ĳ־û������Ƴ�
	 * **/
	@Test
	public void testEvict(){
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 2);
		news1.setTitle("AA");
		news2.setTitle("BB");
		session.evict(news1);
	}
	/*
	 * delete:ִ��ɾ��������ֻҪOID�����ݱ��е�һ����¼��Ӧ���ͻ�׼��ִ��delete����
	 * ��OID�����ݱ���û�ж�Ӧ�ļ�¼���׳��쳣
	 * 
	 * ����ͨ������hibernate�����ļ���hibernate.use_identifier_rollback����Ϊtrue
	 * ��ɾ����������OID��Ϊnull
	 * ***/
	@Test
	public void testDelete(){
//		News news = new News();
//		news.setId(11);
		
		News news = (News) session.get(News.class, 163840);
		session.delete(news);
		System.out.println(news);
	}
	/**
	 * ע�⣺
	 * 1.��OID��Ϊ�յ����ݱ��л�û�к����Ӧ�ļ�¼�����׳�һ���쳣
	 * 2.�˽⣺OIDֵ����id��unsaved-value����ֵ�Ķ���Ҳ����Ϊ��һ���������
	 * **/
	@Test
	public void testSaveOrUpdate(){
		News news = new News("FF","ff",new Date());
		news.setId(11);
		session.saveOrUpdate(news);
		
	}

	/**
	 * update:
	 * 1.������һ���־û����󣬲���Ҫ��ʽ�ĵ���update��������Ϊ�ڵ���transaction��commit����ʱ������ִ��session��flush����
	 * 2.��Ҫ����һ�����������Ҫ��ʽ�ĵ���session��update����.���԰�һ����������Ϊ�־û�����
	 * 
	 * ע��: 1.����Ҫ���µ������������ݱ�ļ�¼�Ƿ�һ�£����ᷢ��update���
	 * �������update��������äĿ�Ĵ���update��䣿��.hbm.xml�ļ���class�ڵ�����select-before-update=true(Ĭ��Ϊfalse)��
	 * ��ͨ������Ҫ���ø�����
	 * 2.�����ݱ���û�ж�Ӧ�ļ�¼������������update�������׳��쳣
	 * 3.�� update() ��������һ���������ʱ, ����� Session �Ļ������Ѿ�������ͬ OID �ĳ־û�����, ���׳��쳣
	 * ��Ϊ��sessio�����в���������OID��ͬ�Ķ���
	 **/
	@Test
	public void testUpdate() {
		News news = (News) session.get(News.class, 1);

		transaction.commit();
		session.close();
//		news.setId(100);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		// news.setAuthor("Sun");
		
		News news2 = (News) session.get(News.class, 1);
		session.update(news);
	}

	/**
	 * get VS load 1.ִ��get�������������ض��󡣶�ִ��load����������ʹ�øö����򲻻�����ִ�в�ѯ������������һ���������
	 * get������������load���ӳټ���
	 * 2.load�������ܻ��׳��������쳣LazyInitializationExecption:����Ҫ��ʼ���������֮ǰ�Ѿ��ر���Session
	 * 
	 * 3.�����ݱ���û�ж�Ӧ�ļ�¼��SessionҲû�б��ر� get����null��load����ʹ�øö�����κ����ԣ�û�����⣻����Ҫ��ʼ�������쳣
	 **/
	@Test
	public void testLoad() {
		News news = (News) session.load(News.class, 10);
		System.out.println(news.getClass().getName());
		// session.close();
		// System.out.println(news);
	}

	@Test
	public void testGet() {
		News news = (News) session.get(News.class, 1);
		// session.close();
		System.out.println(news);
	}

	/**
	 * persist()Ҳ��ִ��insert����
	 * 
	 * ��save()���������� ��persist()����֮ǰ���������Ѿ���id�ˣ��򲻻�ִ��insert�����׳��쳣
	 **/
	@Test
	public void testPersist() {
		News news = new News();
		news.setTitle("EE");
		news.setAuthor("ee");
		news.setId(200);
		news.setDate(new Date());
		System.out.println(news);
		session.persist(news);
		System.out.println(news);
	}

	/**
	 * save()������ 1.��һ����ʱ�����Ϊ�־û����� 2.Ϊ�������id 3.��flush����ʱ�ᷢ��һ��insert���
	 * 4.��save()����֮ǰ���õ�id����Ч�� 5.�־û������id�ǲ��ܱ��޸ĵģ�
	 **/
	@Test
	public void testSave() {
		News news = new News();
		news.setTitle("CC");
		news.setAuthor("cc");
		news.setDate(new Date());
		// news.setId(100);
		System.out.println(news);
		session.save(news);
		System.out.println(news);
		// news.setId(101);
	}

	@Test
	public void testClear() {
		News news = (News) session.get(News.class, 1);
		session.clear();
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news);
	}

	/**
	 * refresh()����ǿ�Ʒ���SELECT��䣬��ʹSession�����ж����״̬�����ݱ��ж�Ӧ�ļ�¼����һ�£�
	 **/
	@Test
	public void testRefresh() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		session.refresh(news);
		System.out.println(news);
	}
	/*
	 * flush:ʹ���ݱ��еļ�¼��Session�����еĶ����״̬����һ�£�Ϊ�˱���һ������ܻᷢ�Ͷ�Ӧ��SQL���
	 * 1.��Transaction��commit()�����У��ȵ���Session��flush()���������ύ����
	 * 2.flush()���ܻᷢ��SQL��䣬�������ύ����
	 * 3.ע�⣺��δ�ύ�������ʽ�ĵ���Session.flush()����֮ǰ��Ҳ�п��ܻ����flush()����
	 * 1).ִ��HQL��QBC��ѯ�����Ƚ���flush()����,�Եõ����ݱ�����µ�����
	 * 2).����¼��id���ɵײ����ݿ�ʹ�������ķ�ʽ���ɵģ����ڵ���save����ʱ�ͻ���������insert��䣬��Ϊsave�����󣬱��뱣֤�����id
	 * �Ǵ��ڵģ�
	 **/

	@Test
	public void testSessionFlush2() {
		News news = new News("Java", "SUN", new Date());
		session.save(news);
	}

	@Test
	public void testSessionFlush() {
		News news = (News) session.get(News.class, 1);
		news.setAuthor("Oracle");

		// session.flush();
		// System.out.println("flush");

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
