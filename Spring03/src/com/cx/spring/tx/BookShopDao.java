package com.cx.spring.tx;

public interface BookShopDao {
	//������ţ���ȡ��ĵ���
	public int findBookPriceByIsbn(String isbn);
	//������Ŀ�棬ʹ��Ŷ�Ӧ�Ŀ���һ
	public void updateBookStock(String isbn);
	//�����û����˻���ʹusername��balance-peice
	public void updateUserAccount(String username,int price);
}
