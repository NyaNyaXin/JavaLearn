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
	 * 获取单个列的值，或做统计查询
	 * 使用queryForObject(String sql, Class<Long> requiredType)方法
	 * **/
	@Test
	public void testQueryForObject2(){
		String sql="SELECT count(id) FROM employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}
	
	/*
	 * 查到实体类的集合
	 * 注意，调用的不是QueryForList方法而是Query
	 * ***/
	@Test
	public void testQueryForList(){
		String sql = "SELECT id,last_name lastName,email From employees WHERE id>?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper,5);
		System.out.println(employees);
	}
	/*
	 * 从数据库中获取一条记录，实际得到对应的一个对象
	 * 注意不是调用queryForObject(String sql, Class<Employee> requiredType, Object... args)方法！
	 * 而需要使用queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)方法
	 * 1.其中的RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2.使用SQL中列的别名完成列名和类的属性名的映射。例如：last_name lastName
	 * 3.不知持级联属性，JdbcTemplate到底是一个JDBC的小工具，而不是ORM框架
	 * **/
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id,last_name lastName,email,dept_id From employees WHERE id=?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee =jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(employee);
	}
	/**
	 * 执行批量更新：批量的INSERT,UPDATE,DELETE
	 * 最后一个参数是Object[]的List类型：因为修改一条记录需要一个Object的数组，修改多条就需要多个Object数组的集合
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
	 * 执行 INSERT,UPDATE,DELETE
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
