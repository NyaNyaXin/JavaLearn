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

		// 1.获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		// 2.更新书的库存
		bookShopDao.updateBookStock(isbn);
		// 3.更新用户的余额
		bookShopDao.updateUserAccount(username, price);
	}

}
