package com.cx.spring.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		departmentDao = ctx.getBean(DepartmentDao.class);
		employeeDao = ctx.getBean(EmployeeDao.class);
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	/*
	 * ʹ�þ�������ʱ������ʹ��update(String sql, SqlParameterSource paramSource) �������и��²���
	 * 1.SQL����еĲ��������������һ�£�
	 * 2.ʹ��SqlParameterSource��BeanPropertySqlParameterSourceʵ������Ϊ����
	 * ***/
	@Test
	public void testnamedParameterJdbcTemplate2(){
		String sql="INSERT INTO employees(last_name,email,dept_id)"
				+ " VALUES(:lastName,:email,:deptid)";
		Employee employee = new Employee();
		employee.setLastName("XYZ");
		employee.setEmail("xyz@163.com");
		employee.setDeptid(3);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
	/**
	 * ����Ϊ���������֣�
	 * 1.�ô������ж��������������ȥ��Ӧ������λ�ã���ֱ�Ӷ�Ӧ�������Ƽ��ɣ�������ά��
	 * 2.ȱ�㣺���鷳
	 * **/
	@Test
	public void testnamedParameterJdbcTemplate(){
		String sql="INSERT INTO employees(last_name,email,dept_id) VALUES(:ln,:email,:deptid)";
		Map<String, Object> map = new HashMap<>();
		map.put("ln", "FF");
		map.put("email", "ff@163.com");
		map.put("deptid", 2);
		namedParameterJdbcTemplate.update(sql, map);
	}
	@Test
	public void testDepartmentDao(){
		
		System.out.println(departmentDao.get(1));
	}
	@Test
	public void testEmployeeDao(){
		
		
		System.out.println(employeeDao.get(5));
	}
	/**
	 * ��ȡ�����е�ֵ������ͳ�Ʋ�ѯ
	 * ʹ��queryForObject(String sql, Class<Long> requiredType)����
	 * **/
	@Test
	public void testQueryForObject2(){
		String sql="SELECT count(id) FROM employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	/*
	 * �鵽ʵ����ļ���
	 * ע�⣬���õĲ���QueryForList��������Query
	 * ***/
	@Test
	public void testQueryForList(){
		String sql = "SELECT id,last_name lastName,email From employees WHERE id>?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper,5);
		System.out.println(employees);
	}
	/*
	 * �����ݿ��л�ȡһ����¼��ʵ�ʵõ���Ӧ��һ������
	 * ע�ⲻ�ǵ���queryForObject(String sql, Class<Employee> requiredType, Object... args)������
	 * ����Ҫʹ��queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)����
	 * 1.���е�RowMapperָ�����ȥӳ���������У����õ�ʵ����ΪBeanPropertyRowMapper
	 * 2.ʹ��SQL���еı�����������������������ӳ�䡣���磺last_name lastName
	 * 3.��֪�ּ������ԣ�JdbcTemplate������һ��JDBC��С���ߣ�������ORM���
	 * **/
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id,last_name lastName,email,dept_id From employees WHERE id=?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee =jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(employee);
	}
	/**
	 * ִ���������£�������INSERT,UPDATE,DELETE
	 * ���һ��������Object[]��List���ͣ���Ϊ�޸�һ����¼��Ҫһ��Object�����飬�޸Ķ�������Ҫ���Object����ļ���
	 * **/
	@Test
	public void testBatchUpdate(){
		String sql="INSERT INTO employees(last_name,email,dept_id) VALUES(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"AA","aa@163.com",1});
		batchArgs.add(new Object[]{"BB","bb@163.com",2});
		batchArgs.add(new Object[]{"CC","cc@163.com",1});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	/**
	 * ִ�� INSERT,UPDATE,DELETE
	 * **/
	@Test
	public void testUpdate(){
		String sql="UPDATE employees SET last_name= ? where id = ?";
		jdbcTemplate.update(sql, "jack",5);
	}
	@Test
	public void testDataSource() throws Exception {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}