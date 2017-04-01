package com.cx.spring.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
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
