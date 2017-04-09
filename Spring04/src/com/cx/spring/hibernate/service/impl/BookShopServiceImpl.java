package com.cx.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.spring.hibernate.dao.BookShopDao;
import com.cx.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * Spring Hibernate���������
	 * 1.�ڷ�����ʼ֮ǰ
	 * ��.��ȡSession
	 * ��.��Session�͵�ǰ�̰߳󶨣���Ҫ�Ϳ�����Dao��ʹ��SessionFactory��getCurrentSession()������
	 * ��ȡSession��
	 * ��.��������
	 * 2.������������������û�г����쳣����
	 * ��.�ύ����
	 * ��.ʹ�͵�ǰ�̰߳󶨵�Session�����
	 * ��.�ر�Session
	 * 3.�����������쳣����
	 * ��.�ع�����
	 * ��.ʹ�͵�ǰ�̰߳󶨵�Session�����
	 * ��.�ر�Session
	 * **/
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
