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
				//调用存储过程。转至JDBC
			}
		});
	}
	/**
	 * evict:从session缓存中把指定的持久化对象移除
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
	 * delete:执行删除操作。只要OID和数据表中的一条记录对应，就会准备执行delete操作
	 * 若OID在数据表中没有对应的记录则抛出异常
	 * 
	 * 可以通过设置hibernate配置文件的hibernate.use_identifier_rollback属性为true
	 * 是删除对象后把其OID置为null
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
	 * 注意：
	 * 1.若OID不为空但数据表中还没有和其对应的记录，会抛出一个异常
	 * 2.了解：OID值等于id的unsaved-value属性值的对象，也被认为是一个游离对象
	 * **/
	@Test
	public void testSaveOrUpdate(){
		News news = new News("FF","ff",new Date());
		news.setId(11);
		session.saveOrUpdate(news);
		
	}

	/**
	 * update:
	 * 1.若更新一个持久化对象，不需要显式的调用update方法，因为在调用transaction的commit方法时，会先执行session的flush方法
	 * 2.若要更新一个游离对象，需要显式的调用session的update方法.可以把一个游离对象变为持久化对象
	 * 
	 * 注意: 1.无论要更新的游离对象和数据表的记录是否一致，都会发送update语句
	 * 如何能让update方法不再盲目的触发update语句？在.hbm.xml文件的class节点设置select-before-update=true(默认为false)，
	 * 但通常不需要设置该属性
	 * 2.若数据表中没有对应的记录，但还调用了update方法会抛出异常
	 * 3.当 update() 方法关联一个游离对象时, 如果在 Session 的缓存中已经存在相同 OID 的持久化对象, 会抛出异常
	 * 因为在sessio缓存中不能有两个OID相同的对象！
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
	 * get VS load 1.执行get方法会立即加载对象。而执行load方法，若不使用该对象，则不会立即执行查询操作，而返回一个代理对象
	 * get是立即检索，load是延迟检索
	 * 2.load方法可能会抛出懒加载异常LazyInitializationExecption:在需要初始化代理对像之前已经关闭了Session
	 * 
	 * 3.若数据表中没有对应的记录，Session也没有被关闭 get返回null，load若不使用该对象的任何属性，没有问题；若需要初始化则抛异常
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
	 * persist()也会执行insert操作
	 * 
	 * 和save()方法的区别： 在persist()方法之前，若对象已经有id了，则不会执行insert而会抛出异常
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
	 * save()方法： 1.是一个临时对象变为持久化对象 2.为对象分配id 3.在flush缓存时会发送一条insert语句
	 * 4.在save()方法之前设置的id是无效的 5.持久化对象的id是不能被修改的！
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
	 * refresh()：会强制发送SELECT语句，以使Session缓存中对象的状态和数据表中对应的记录保持一致！
	 **/
	@Test
	public void testRefresh() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		session.refresh(news);
		System.out.println(news);
	}
	/*
	 * flush:使数据表中的记录和Session缓存中的对象的状态保持一致，为了保持一致则可能会发送对应的SQL语句
	 * 1.在Transaction的commit()方法中：先调用Session的flush()方法，再提交事务
	 * 2.flush()可能会发送SQL语句，但不会提交事务
	 * 3.注意：在未提交事务或显式的调用Session.flush()方法之前，也有可能会进行flush()操作
	 * 1).执行HQL或QBC查询，会先进行flush()操纵,以得到数据表的最新的数据
	 * 2).若记录的id是由底层数据库使用自增的方式生成的，则在调用save方法时就会立即发送insert语句，因为save方法后，必须保证对象的id
	 * 是存在的！
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
