package com.cx.spring.tx;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransationTest {
	
	private ApplicationContext ctx = null;
	BookShopDao bookShopDao = null;
	BookShopService bookShopService = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
		bookShopService = ctx.getBean(BookShopService.class);
	}

	@Test
	public void testBookShopFindPriceByIsbn(){
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}
	@Test
	public void testBookShopDaoUpdateBookStock(){
		bookShopDao.updateBookStock("1001");
	}
	
	@Test
	public void testBookShopDaoUpdateUserAccount(){
		bookShopDao.updateUserAccount("AA", 200);
	}
	
	@Test
	public void testBookShopService(){
		bookShopService.purchase("AA", "1001");
	}

}
