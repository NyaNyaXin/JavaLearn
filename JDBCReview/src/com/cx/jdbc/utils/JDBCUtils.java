package com.cx.jdbc.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;


public class JDBCUtils {
	// 获取数据库连接
	public static Connection getConnection() throws Exception {
		// 1.获取数据库连接的基本信息
		// 1.1创建Properties对象，以流的形式，将配置文件中的信息读入到程序中
		Properties info = new Properties();
		info.load(new FileInputStream("jdbc.properties"));
		// 1.2提供四个基本信息url,driverClass,user,password
		String url = info.getProperty("url");
		String driverClass = info.getProperty("driverClass");
		String user = info.getProperty("user");
		String password = info.getProperty("password");
		// 2.加载驱动
		Class.forName(driverClass);
		// 3.使用DriverManager的getConnection(url,user,password)方法，获取数据库连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	//关闭数据库连接
	public static void close(ResultSet rs,Statement st,Connection conn) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
