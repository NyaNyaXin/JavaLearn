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
	 * ����Statement�ӿ�
	 * 	�ýӿڿɸ��� INSERT,UPDATE,DELETE
	 * 	
	 * **/
	@Test
	public void testUpdate() {
		//1.��ȡ���ݿ�����
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
	//��ѯһ��ֵ
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
			 * ͨ��ResultSet����getMetaData()����һ�������Ԫ����:ResultSetMetaData
			 * 	1.getColumnCount():���ؽ����������
			 * 	2.getColumnLabel()�������еı���
			 * **/
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			if(rs.next()) {
				for(int i=0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);//��Ӧ�е�ֵ
					//String columnName = resultSetMetaData.getColumnName(i+1);//һ�㲻��
					String columnName = resultSetMetaData.getColumnLabel(i+1);
					//ʹ��PropertyUtils��ָ�������t��ָ������columnNameֵ�趨Ϊָ����columnVal
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
	//��ѯ���ֵ
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
			 * ͨ��ResultSet����getMetaData()����һ�������Ԫ����:ResultSetMetaData
			 * 	1.getColumnCount():���ؽ����������
			 * 	2.getColumnLabel()�������еı���
			 * **/
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (rs.next()) {
				T t = clazz.newInstance();
				for(int i= 0;i<columnCount;i++) {
					Object columnVal = rs.getObject(i+1);
					//System.out.println(columnVal.toString());
					//String columnName = resultSetMetaData.getColumnName(i+1);//һ�㲻��
					String columnName = resultSetMetaData.getColumnLabel(i+1);
					//ʹ��PropertyUtils��ָ�������t��ָ������columnNameֵ�趨Ϊָ����columnVal
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
