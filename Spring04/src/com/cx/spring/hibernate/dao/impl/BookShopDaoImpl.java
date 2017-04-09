package com.cx.spring.hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cx.spring.hibernate.dao.BookShopDao;
import com.cx.spring.hibernate.exception.BookStockException;
import com.cx.spring.hibernate.exception.UserAccountException;
@Component
public class BookShopDaoImpl implements BookShopDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	//不推荐使用这种方式HibernateTemplate和这种HibernateDaoSupport方式
	//因为这样会导致Dao和Spring的API进行耦合，可移植性变差
	//private HibernateTemplate hibernateTemplate;
	
	//获取和当前线程绑定的Session
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "SELECT b.price FROM Book b WHERE b.isbn = ?";
		Query query = getSession().createQuery(hql).setString(0, isbn);
		return (Integer)query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		//验证书的库存是否充足
		String hql2 = "SELECT b.stock FROM Book b where b.isbn = ?";
		int stock = (int) getSession().createQuery(hql2).setString(0, isbn).uniqueResult();
		if(stock==0){
			throw new BookStockException("库存不足");
		}
		String hql = "UPDATE Book b SET b.stock=b.stock-1 WHERE b.isbn=?";
		getSession().createQuery(hql).setString(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String username, int price) {
		//验证余额是否足够
		String hql2 = "SELECT a.balance FROM Account a WHERE a.username=?";
		int balance = (int) getSession().createQuery(hql2).setString(0, username).uniqueResult(); 
		if(balance < price){
			throw new UserAccountException("您的余额不足，请充值");
		}
		String sql = "Update Account a SET a.balance = a.balance-? WHERE a.username=?";
		getSession().createQuery(sql).setInteger(0, price).setString(1, username).executeUpdate();
	}

}
