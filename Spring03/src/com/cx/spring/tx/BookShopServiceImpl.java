package com.cx.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDao bookShopDao;

	@Override
	//�������ע��
	/**
	 * ʹ��propagation����ָ������Ĵ�����Ϊ������ǰ�����񷽷�������һ�����񷽷�����ʽ���ʹ������
	 * Ĭ��ȡֵΪREQUIRED����ʹ�õ��÷���������
	 * REQUIRES_NEWʹ���Լ������񣬵��÷�����������񱻹���
	 * */
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void purchase(String username, String isbn) {
		//1.��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2.������Ŀ��
		bookShopDao.updateBookStock(isbn);
		//3.�����û������
		bookShopDao.updateUserAccount(username, price);
	}

}
