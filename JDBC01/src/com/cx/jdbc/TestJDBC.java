package com.cx.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class TestJDBC {

	/**
	 * DriverManager是驱动的管理类 1.可以通过重载的getConnection方法获取数据库连接，较为方便
	 * 2.可以同时管理多个驱动程序：若注册了多个数据库连接则调用getConnection 方法的传入参数不同会返回不同的数据库连接
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDriverManager() throws Exception {
		// 准备连接数据库的参数
		String driverClass = null;// 驱动的全类名
		String jdbcUrl = null;// url
		String user = null;// user
		String password = null;// password
		// 读取配置文件
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		// 加载数据库驱动程序(对应的Driver实现类中有对应的注册驱动的静态代码块)
		// DriverManager.registerDriver((Driver)
		// Class.forName(driverClass).newInstance());
		Class.forName(driverClass);
		// 通过DriverManager的getConnection()获取数据库连接
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(connection);
	}

	/*
	 * Driver是一个接口，数据库厂商必须实现，能从其中获取数据库连接
	 ****/
	@Test
	public void testDriver() throws SQLException {
		// 创建Driver接口实现类对象
		Driver driver = new com.mysql.jdbc.Driver();
		// 准备连接数据库的基本信息
		String url = "jdbc:mysql://localhost:3306/test";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "123456");
		// 调用Driver接口的connect方法获取数据库连接
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
	}

	/**
	 * 通过把数据库驱动的全类名，url，user，password放入配置文件中，修改配置文件的方式,将程序与数据库解耦
	 * 
	 * @throws Exception
	 **/
	public Connection connection() throws Exception {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		// 读取配置文件
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		// 通过反射创建数据库厂商对应的Driver对象
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Connection connection = driver.connect(jdbcUrl, info);
		return connection;

	}

	@Test
	public void testConnection() throws Exception {
		System.out.println(connection());
	}

}
