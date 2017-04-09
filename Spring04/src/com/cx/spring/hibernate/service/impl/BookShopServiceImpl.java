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
	 * Spring Hibernate事务的流程
	 * 1.在方法开始之前
	 * ①.获取Session
	 * ②.把Session和当前线程绑定，这要就可以在Dao中使用SessionFactory的getCurrentSession()方法来
	 * 获取Session了
	 * ③.开启事务
	 * 2.若方法正常结束，即没有出现异常，则
	 * ①.提交事务
	 * ②.使和当前线程绑定的Session解除绑定
	 * ③.关闭Session
	 * 3.若方法出现异常，则
	 * ①.回滚事务
	 * ②.使和当前线程绑定的Session解除绑定
	 * ③.关闭Session
	 * **/
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
