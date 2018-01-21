package com.cx.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet:"写入并复制"
 * 注意：
 * 添加操作多时，效率低，因为每次添加时都会进行复制，开销非常大。并发迭代操作时可以选择
 * */
public class TestCopyOnArrayList {
	public static void main(String[] args) {
		HelloThread ht = new HelloThread();
		for(int i=0;i<10;i++){
			new Thread(ht).start();;
		}
	}
}
class HelloThread implements Runnable{
	
//	private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	static{
		list.add("AA");
		list.add("BB");
		list.add("CC");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			list.add("AA");
		}
	}
	
}
