package com.cx.jdbc.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;


public class JDBCUtils {
	// ��ȡ���ݿ�����
	public static Connection getConnection() throws Exception {
		// 1.��ȡ���ݿ����ӵĻ�����Ϣ
		// 1.1����Properties������������ʽ���������ļ��е���Ϣ���뵽������
		Properties info = new Properties();
		info.load(new FileInputStream("jdbc.properties"));
		// 1.2�ṩ�ĸ�������Ϣurl,driverClass,user,password
		String url = info.getProperty("url");
		String driverClass = info.getProperty("driverClass");
		String user = info.getProperty("user");
		String password = info.getProperty("password");
		// 2.��������
		Class.forName(driverClass);
		// 3.ʹ��DriverManager��getConnection(url,user,password)��������ȡ���ݿ�����
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	//�ر����ݿ�����
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
