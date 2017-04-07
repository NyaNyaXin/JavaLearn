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
		//1.lazyȡֵΪproxy��false�ֱ����Զ�Ӧ�����Բ����ӳټ�������������
		//2.fetchȡֵΪjoin��ʾʹ�������������ӵķ�ʽ��ʼ��n������1��һ�˵�����
		//����lazy����
		//3.��������Ҫ������1��һ�˵�classԪ���У�
		//<class name="Customer" table="CUSTOMERS" lazy="true" batch-size="5">
		//���ã�һ�γ�ʼ��1����һ�˴������ĸ���
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
		//----------------------------Set���ϵ�Fetch����-------------------------
		//ȷ����ʼ��orders���ϵķ�ʽ
		//1.Ĭ��ֵΪselect��ͨ�������ķ�ʽ����ʼ��setԪ��
		//2.����ȡֵΪsubselect��ͨ���Ӳ�ѯ�ķ�ʽ����ʼ�����е�set���ϣ���ʱlazy��Ч��batch-sizeʧЧ
		//�Ӳ�ѯ��Ϊwhere�Ӿ��in����������
		//�Ӳ�ѯ��ѯ����1��һ�˵�id
		//3��ȡֵΪjoin��
		//3.1�ڼ���1��һ�˶���ʱ��ʹ�������������ӣ�ʹ���������ӽ��в�ѯ�ҰѼ������Խ��г�ʼ�����ķ�ʽ����n��һ�˵ļ�������
		//3.2����lazy����
		//3.3HQL��ѯ����fetch=join��ȡֵ
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
		//-----------------------setԪ�ص�batch-size����------------------------------
		//����һ�γ�ʼ��set���ϵ�����
	}
	@Test
	public void testOneToManyLevelStrategy(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		System.out.println(customer.getCustomerName());
		
		Hibernate.initialize(customer.getOrders());
		//------------------------set��lazy����-------------------------------------
		//1.һ�Զ���Զ�ļ������ԣ�Ĭ��ʹ�������ؼ�������
		//2.����ͨ������set��lazy�������޸�Ĭ�ϵļ�������,Ĭ��Ϊtrue��������������Ϊfalse
		//3.lazy����������Ϊextra����ǿ���ӳټ���,��ȡֵ�ᾡ���ܵ��ӳټ��ϳ�ʼ����ʱ��
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
