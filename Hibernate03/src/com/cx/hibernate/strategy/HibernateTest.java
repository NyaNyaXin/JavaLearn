package com.cx.hibernate.strategy;

import java.util.List;

import org.hibernate.Hibernate;
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
	public void testMany2OneStrategy(){
//		Order order = (Order) session.get(Order.class, 1);
//		System.out.println(order.getCustomer().getCustomerName());
		
		List<Order> orders = session.createQuery("FROM Order o").list();
		for(Order order :orders){
			if(order.getCustomer()!=null){
				System.out.println(order.getCustomer().getCustomerName());
			}
		}
		//1.lazy取值为proxy和false分别代表对对应的属性采用延迟检索和立即检索
		//2.fetch取值为join表示使用迫切左外连接的方式初始化n关联的1的一端的属性
		//忽略lazy属性
		//3.该属性需要设置在1那一端的class元素中：
		//<class name="Customer" table="CUSTOMERS" lazy="true" batch-size="5">
		//作用：一次初始化1的这一端代理对象的个数
	}
	@Test
	public void testSetFetch2(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getOrders().size());
	}
	
	@Test
	public void testSetFetch(){
		List<Customer> customers = session.createQuery("FROM Customer").list();
		System.out.println(customers.size());
		
		for(Customer customer:customers)
		{
			if(customer.getOrders()!=null){
				System.out.println(customer.getOrders().size());
			}
		}
		//----------------------------Set集合的Fetch属性-------------------------
		//确定初始化orders集合的方式
		//1.默认值为select。通过正常的方式来初始化set元素
		//2.可以取值为subselect。通过子查询的方式来初始化所有的set集合，此时lazy有效但batch-size失效
		//子查询作为where子句的in的条件出现
		//子查询查询所有1的一端的id
		//3若取值为join则
		//3.1在加载1的一端对象时，使用迫切左外连接（使用左外连接进行查询且把集合属性进行初始化）的方式检索n的一端的集合属性
		//3.2忽略lazy属性
		//3.3HQL查询忽略fetch=join的取值
	}
	@Test
	public void testSetBatchSize(){
		List<Customer> customers = session.createQuery("FROM Customer").list();
		System.out.println(customers.size());
		
		for(Customer customer:customers)
		{
			if(customer.getOrders()!=null){
				System.out.println(customer.getOrders().size());
			}
		}
		//-----------------------set元素的batch-size属性------------------------------
		//设置一次初始化set集合的数量
	}
	@Test
	public void testOneToManyLevelStrategy(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		
		Hibernate.initialize(customer.getOrders());
		//------------------------set的lazy属性-------------------------------------
		//1.一对多或多对多的集合属性，默认使用懒加载检索策略
		//2.可以通过设置set的lazy属性来修改默认的检索策略,默认为true，并不建议设置为false
		//3.lazy还可以设置为extra，增强的延迟检索,该取值会尽可能的延迟集合初始化的时机
		Order order = new Order();
		order.setOrderId(1);
		System.out.println(customer.getOrders().size());
		System.out.println(customer.getOrders().contains(order));
	}
	@Test
	public void testClassLevelStrategy(){
		Customer customer = (Customer) session.load(Customer.class, 1);
		System.out.println(customer.getClass());
		System.out.println(customer.getCustomerId());
		System.out.println(customer.getCustomerName());
	}
}
