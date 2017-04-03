package com.cx.spring.tx.xml.service.impl;

import java.util.List;

import com.cx.spring.tx.xml.service.BookShopService;
import com.cx.spring.tx.xml.service.Cashier;

public class CashierImpl implements Cashier {
	private BookShopService bookShopService;

	/**
	 * @param bookShopService
	 *            the bookShopService to set
	 */
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	@Override
	public void checkout(String username, List<String> isbns) {
		for (String isbn : isbns) {
			bookShopService.purchase(username, isbn);
		}
	}

}
