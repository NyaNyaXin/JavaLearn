package com.cx.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import com.cx.jdbc.entities.Customer;
import com.cx.jdbc.utils.JDBCUtils;

class TestPreparedStatement {

	
	/**
	 * 实现一个通用的UPDATE,INSERT,DELETE的操作的方法
	 * **/
	@Test
	public void update(Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("update customer set name = ? where id = ?");
			//填充占位符
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, ps, conn);
		}
		
	}
	//通用的查询操作，返回一个对象
	public<T> T getInstance(Class<T> clazz,Object...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select id id,name name from customer where id = ?");
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				T t  = clazz.newInstance();
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for(int i =0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);
					String columnLabel = resultSetMetaData.getColumnLabel(i+1);
					PropertyUtils.setProperty(t, columnLabel, columnVal);
				}
				return t;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return null;
	}
	
	//通用查询操作，返回一个对象集合
	public<T> List<T> getForList(Class<T> clazz,Object...args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("select id id,name name from customer where name = ?");
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (rs.next()) {
				T t = clazz.newInstance();
				for(int i=0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);
					String columnLabel = resultSetMetaData.getColumnLabel(i+1);
					PropertyUtils.setProperty(t, columnLabel, columnVal);
				}
				list.add(t);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, ps, conn);
		}
		return list;
	}
	public static void main(String[] args) {
		TestPreparedStatement test = new TestPreparedStatement();
		//test.update("chenin",2);
		 List<Customer> forList = test.getForList(Customer.class, "chenxin");
		System.out.println(forList);
	}

}
