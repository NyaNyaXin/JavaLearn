package com.cx.jdbc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import com.cx.jdbc.entities.Customer;
import com.cx.jdbc.utils.JDBCUtils;

import sun.security.pkcs11.Secmod.DbMode;


class TestStatementAndResultSet {
	/**
	 * 测试Statement接口
	 * 	该接口可负责 INSERT,UPDATE,DELETE
	 * 	
	 * **/
	@Test
	public void testUpdate() {
		//1.获取数据库连接
		Connection conn = null;
		Statement st = null;
		String sql = "update customer set name='chenxin' where id = 1";
		try {
			conn = JDBCUtils.getConnection();
			st = conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, st, conn);
		}
	}
	//查询一个值
	public<T> T get(Class<T> clazz) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		T t = null;
		try {
			t = clazz.newInstance();
			conn=JDBCUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select id id,name name from customer where id= 1");
			/**
			 * 通过ResultSet调用getMetaData()返回一个结果集元数据:ResultSetMetaData
			 * 	1.getColumnCount():返回结果集的列数
			 * 	2.getColumnLabel()：返回列的别名
			 * **/
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			if(rs.next()) {
				for(int i=0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);//对应列的值
					//String columnName = resultSetMetaData.getColumnName(i+1);//一般不用
					String columnName = resultSetMetaData.getColumnLabel(i+1);
					//使用PropertyUtils将指定对象的t的指定属性columnName值设定为指定的columnVal
					PropertyUtils.setProperty(t, columnName, columnVal);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, st, conn);
		}
		
		return t;
	}
	//查询多个值
	public<T> List<T> getInstances(Class<T> clazz){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		
		
		try {
			conn = JDBCUtils.getConnection();
			st=conn.createStatement();
			rs = st.executeQuery("select id id,name name from customer");
			/**
			 * 通过ResultSet调用getMetaData()返回一个结果集元数据:ResultSetMetaData
			 * 	1.getColumnCount():返回结果集的列数
			 * 	2.getColumnLabel()：返回列的别名
			 * **/
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (rs.next()) {
				T t = clazz.newInstance();
				for(int i= 0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);
					//System.out.println(columnVal.toString());
					//String columnName = resultSetMetaData.getColumnName(i+1);//一般不用
					String columnName = resultSetMetaData.getColumnLabel(i+1);
					//使用PropertyUtils将指定对象的t的指定属性columnName值设定为指定的columnVal
					PropertyUtils.setProperty(t, columnName, columnVal);
				}
				list.add(t);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, st, conn);
		}
		
		return list;
	}
	public static void main(String[] args) {
		TestStatementAndResultSet test =new TestStatementAndResultSet();
		List<Customer> instances = test.getInstances(Customer.class);
		System.out.println(instances);
	}
}
