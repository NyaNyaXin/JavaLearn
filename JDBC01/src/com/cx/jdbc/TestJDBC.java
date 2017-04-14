package com.cx.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class TestJDBC {

	/*
	 * Driver��һ���ӿڣ����ݿ⳧�̱���ʵ�֣��ܴ����л�ȡ���ݿ�����
	 ****/
	@Test
	public void testDriver() throws SQLException {
		// ����Driver�ӿ�ʵ�������
		Driver driver = new com.mysql.jdbc.Driver();
		// ׼���������ݿ�Ļ�����Ϣ
		String url = "jdbc:mysql://localhost:3306/test";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "123456");
		// ����Driver�ӿڵ�connect������ȡ���ݿ�����
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
	}

	/**
	 * ͨ�������ݿ�������ȫ������url��user��password���������ļ��У��޸������ļ��ķ�ʽ,�����������ݿ����
	 * 
	 * @throws Exception
	 **/
	public Connection connection() throws Exception {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//��ȡ�����ļ�
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties  =new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Connection connection = driver.connect(jdbcUrl, info);
		return connection;
		
	}
	
	@Test
	public void testConnection() throws Exception{
		System.out.println(connection());
	}

}
