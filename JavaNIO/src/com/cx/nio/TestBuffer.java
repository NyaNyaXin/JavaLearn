package com.cx.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/*
 * һ��������(Buffer):��Java NIO�и������ݵĴ�ȡ���������������顣���ڴ洢��ͬ���͵�����
 * 
 * �����������Ͳ�ͬ(boolean����)���ṩ����Ӧ���͵Ļ�����
 * ByteBuffer		
 * CharBuffer
 * ShortUbffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * �����������Ĺ���ʽ����һ�£�ͨ��allocate()��ȡ������
 * 
 * ������������ȡ���ݵ��������ķ���
 * put():�������ݵ���������
 * get():��ȡ�������е�����
 * �ġ��������е��ĸ���������
 * capacity:��������ʾ�����������洢���ݵ�������һ���������ܸı䡣
 * limit:���ޣ���ʾ�������п��Բ������ݵĴ�С��(limit�����ݲ��ܽ��ж�д)��
 * position:λ�ã���ʾ�����������ڲ������ݵ�λ�á�
 * 
 * mark:��ǣ���ʾ��¼��ǰposition��λ�á�����ͨ��reset()�ָ���mark��λ��
 * 
 * 0<=mark<=position<=limit<=capacity
 * 
 * �塢ֱ�ӻ��������ֱ�ӻ�����
 * ��ֱ�ӻ�������ͨ��allocate()�������仺��������������������JVM���ڴ���
 * ֱ�ӻ�������ͨ��allocateDirect()��������ֱ�ӻ��������������������ڲ���ϵͳ�������ڴ��С��������Ч��
 * 
 * */
public class TestBuffer {
	
	@Test
	public void test3(){
		//����ֱ�ӻ�����
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		//�鿴��ǰ�������Ƿ�Ϊֱ�ӻ�����
		System.out.println(buf.isDirect());
	}
	
	@Test
	public void test2(){
		String str="abcde";
		ByteBuffer buf=  ByteBuffer.allocate(1024);
		buf.put(str.getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		//mark���
		buf.mark();
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(buf.position());
		
		//reset():�ָ���mark��λ��
		buf.reset();
		System.out.println(buf.position());
		//�жϻ��������Ƿ���ʣ������
		if(buf.hasRemaining()){
			//��ȡ�������п��Բ���������
			System.out.println(buf.remaining());
			}
	}
	@Test
	public void test1(){
		
		String str="abcde";
		//1.����һ��ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-------------------allocate()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//2.����put()�������ݵ���������ȥ
		buf.put(str.getBytes());
		System.out.println("-------------------put()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//3.�л��ɶ�ȡ���ݵ�ģʽ
		buf.flip();
		System.out.println("-------------------flip()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//4.����get()������ȡ������������
		byte[] dst = new byte [buf.limit()];
		buf.get(dst);
		System.out.println("-------------------get()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println(new String(dst, 0, dst.length));
		
		//5.rewind():���ظ�������
		buf.rewind();
		System.out.println("-------------------rewind()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//6.��ջ�������clear().���ǻ������е�������Ȼ���ڣ����Ǵ��ڡ���������״̬
		buf.clear();
		System.out.println("-------------------clear()--------------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println((char)buf.get());
	}
}
