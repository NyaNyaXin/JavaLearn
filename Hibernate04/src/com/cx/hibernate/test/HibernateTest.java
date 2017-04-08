package com.cx.hibernate.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	public void testQueryCache(){
		Query query = session.createQuery("FROM Employee");
		query.setCacheable(true);
		List<Employee> employees = query.list();
		System.out.println(employees.size());
		employees = query.list();
		System.out.println(employees);
		
		Criteria criteria  = session.createCriteria(Employee.class);
		criteria.setCacheable(true);
	}
	@Test
	public void testCollectionSecondLevelCache() {
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getName());
		System.out.println(department.getEmps().size());

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		Department department2 = (Department) session.get(Department.class, 1);
		System.out.println(department2.getName());
		System.out.println(department2.getEmps().size());

	}

	@Test
	public void testHibernateSecondLevelCache() {
		Employee employee = (Employee) session.get(Employee.class, 1);
		System.out.println(employee);

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		Employee employee2 = (Employee) session.get(Employee.class, 1);
		System.out.println(employee2);
	}

	@Test
	public void testHQLUpdate() {
		String hql = "DELETE FROM Department d where d.id = :id";
		session.createQuery(hql).setInteger("id", 27).executeUpdate();
	}

	@Test
	public void testNativeSql() {
		String sql = "INSERT INTO gg_department VALUES(?,?)";

		Query query = session.createSQLQuery(sql);
		query.setInteger(0, 27).setString(1, "AAA").executeUpdate();

	}

	@Test
	public void testQBC4() {
		Criteria criteria = session.createCriteria(Employee.class);
		// 1.添加排序
		criteria.addOrder(Order.asc("salary"));
		criteria.addOrder(Order.desc("email"));
		// 2.添加翻页方法
		int pageSize = 2;
		int pageNum = 1;
		criteria.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();

	}

	@Test
	public void testQBC3() {
		Criteria criteria = session.createCriteria(Employee.class);
		// 统计查询：使用Projection:可以由Projections的静态方法得到
		criteria.setProjection(Projections.max("salary"));
		System.out.println(criteria.uniqueResult());
	}

	@Test
	public void testQBC2() {
		Criteria criteria = session.createCriteria(Employee.class);
		// 1.AND:使用 Conjunction表示
		// Conjunction本身就是一个Criteria对象
		// 且其中可以添加Criteria对象
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.like("name", "a", MatchMode.ANYWHERE));
		Department department = new Department();
		department.setId(1);
		conjunction.add(Restrictions.eq("dept", department));
		System.out.println(conjunction);
		// 2.OR
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.ge("salary", 0F));
		disjunction.add(Restrictions.isNull("email"));
		criteria.add(conjunction);
		criteria.add(disjunction);
		System.out.println(criteria);
		criteria.list();
	}

	@Test
	public void testQBC() {
		// 1.创建一个Criteria对象
		Criteria criteria = session.createCriteria(Employee.class);
		// 2.添加查询条件:在QBC中查询条件使用Criterion来表示
		// Criterion可以通过Restrictions对象的静态方法得到
		criteria.add(Restrictions.eq("email", "chen@163.com"));
		criteria.add(Restrictions.gt("salary", 0F));

		// 3.执行查询
		Employee employee = (Employee) criteria.uniqueResult();
		System.out.println(employee);
	}

	@Test
	public void testLeftJoin() {
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN d.emps";

		Query query = session.createQuery(hql);
		List<Department> departments = query.list();
		for (Department department : departments) {
			System.out.println(department.getName());
		}

		// List<Object[]> result =query.list();
		// //result = new ArrayList<>(new LinkedHashSet<>(result));
		// System.out.println(result);
		// for(Object[] objects : result){
		// System.out.println(Arrays.asList(objects));
		// }
	}

	@Test
	public void testLeftJoinFetch() {
		// String hql="SELECT DISTINCT d FROM Department d LEFT JOIN FETCH
		// d.emps";
		String hql = "FROM  Department d INNER JOIN FETCH d.emps";
		Query query = session.createQuery(hql);
		List<Department> departments = query.list();
		departments = new ArrayList<>(new LinkedHashSet(departments));
		System.out.println(departments.size());
		for (Department department : departments) {
			System.out.println(department.getName() + department.getEmps().size());
		}
	}

	@Test
	public void testGroupBy() {
		String hql = "SELECT min(e.salary),max(e.salary) FROM Employee e GROUP BY e.dept HAVING min(e.salary) >:minSal";
		Query query = session.createQuery(hql).setFloat("minSal", 100);
		List<Object[]> result = query.list();
		for (Object[] objects : result) {
			System.out.println(Arrays.asList(objects));
		}
	}

	@Test
	public void testFieldQuery2() {
		String hql = "SELECT new Employee(e.salary,e.email,e.dept) FROM Employee e WHERE e.dept = :dept";
		Query query = session.createQuery(hql);
		Department department = new Department();
		department.setId(1);
		List<Employee> result = query.setEntity("dept", department).list();
		for (Employee employee : result) {
			System.out.println(employee.getId() + employee.getEmail() + employee.getSalary() + employee.getDept());
		}
	}

	@Test
	public void testFieldQuery() {
		String hql = "SELECT e.email,e.salary,e.dept FROM Employee e WHERE e.dept = :dept";
		Query query = session.createQuery(hql);
		Department department = new Department();
		department.setId(1);
		List<Object[]> result = query.setEntity("dept", department).list();
		for (Object[] objects : result) {
			System.out.println(Arrays.asList(objects));
		}
	}

	@Test
	public void testNamedQuery() {
		Query query = session.getNamedQuery("salaryEmps");
		List<Employee> employees = query.setFloat("minSal", 0).setFloat("maxSal", 1000).list();
		System.out.println(employees.size());
	}

	@Test
	public void testPageQuery() {
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		int pageNum = 4;
		int pageSize = 2;
		List<Employee> emps = query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();

		System.out.println(emps);
	}

	@Test
	public void testHQLNamesParameter() {
		// 1.创建Query对象
		// 基于命名参数
		String hql = "FROM Employee e WHERE e.salary>:sql and e.email LIKE :email ORDER BY e.salary";
		Query query = session.createQuery(hql);
		// 2.绑定参数
		query.setFloat("sql", 200).setString("email", "%c%");
		// 3.执行查询
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

	@Test
	public void testHQL() {
		// 1.创建Query对象
		// 基于位置的参数
		String hql = "FROM Employee e WHERE e.salary>? and e.email LIKE ? and e.dept=?";
		Query query = session.createQuery(hql);
		// 2.绑定参数
		// Query对象调用setXxx方法，支持方法链的编程风格
		Department dept = new Department();
		dept.setId(1);
		query.setFloat(0, 200).setString(1, "%c%").setEntity(2, dept);
		// 3.执行查询
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

}
