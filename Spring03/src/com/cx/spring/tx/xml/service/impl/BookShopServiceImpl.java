package com.cx.spring.tx.xml.service.impl;

import com.cx.spring.tx.xml.BookShopDao;
import com.cx.spring.tx.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {
	private BookShopDao bookShopDao;

	/**
	 * @param bookShopDao
	 *            the bookShopDao to set
	 */
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void purchase(String username, String isbn) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1.��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		// 2.������Ŀ��
		bookShopDao.updateBookStock(isbn);
		// 3.�����û������
		bookShopDao.updateUserAccount(username, price);
	}

}
