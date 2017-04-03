package com.cx.spring.tx;

import static org.junit.Assert.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	@Autowired
	private BookShopDao bookShopDao;

	@Override
	//�������ע��
	/**
	 * 1.ʹ��propagation����ָ������Ĵ�����Ϊ������ǰ�����񷽷�������һ�����񷽷�����ʽ���ʹ������
	 * Ĭ��ȡֵΪREQUIRED����ʹ�õ��÷���������
	 * REQUIRES_NEWʹ���Լ������񣬵��÷�����������񱻹���
	 * 2.isolationָ������ĸ��뼶����õ�ȡֵΪREAD_COMMITTED(�����ύ)
	 * 3.Ĭ�������Spring������ʽ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ�������
	 * ͨ�������ȡĬ��ֵ����(noRollbackFor�ȣ���������Ļع�����)
	 * 4.ʹ��readOnlyָ�������Ƿ�Ϊֻ������ʾ�������ֻ��ȡ���ݵ�����������, �������԰������ݿ������Ż�����.
	 * �������һ��ֻ��ȡ���ݿ�ֵ�ķ�����Ӧ����readOnly=true
	 * 5.ʹ��timeout����ָ��ǿ�ƻع�֮ǰ�������ռ�õ�ʱ��
	 * */
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,
	isolation=Isolation.READ_COMMITTED
	//,noRollbackFor={UserAccountException.class}
	,readOnly=false
	,timeout=3
	)
	public void purchase(String username, String isbn) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1.��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2.������Ŀ��
		bookShopDao.updateBookStock(isbn);
		//3.�����û������
		bookShopDao.updateUserAccount(username, price);
	}

}
