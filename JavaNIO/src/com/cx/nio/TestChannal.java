package com.cx.nio;
/*
 * һ��ͨ����Channel��������Դ�ڵ���Ŀ��ڵ�����ӡ���Java NIO�и��𻺳��������ݵĴ��䡣Channel�����洢���ݣ������Ҫ��ϻ��������д���
 * 
 * ����ͨ����һЩ��Ҫʵ����
 * 		java.nio.channels.Channel
 * 			|--FileChannel
 * 			|--SocketChannel
 * 			|--ServerSocketChannel
 * 			|--DatagramChannel
 * 
 * ������ȡͨ��
 * 1.Java���֧��ͨ�������ṩ��getChannel()����
 * 			����IO
 * 			FileInputStream/FileOutputStream
 * 			RandomAccessFile
 * 			����IO
 * 			Socket
 * 			Server Socket
 * 			DatagramSocket
 * 2.��jdk1.7�е�NIO.2��Ը���ͨ���ṩ��һ����̬����open()
 * 3.��jdk1.7�е�NIO.2��Files�������newByteChannel()
 * 
 * �ġ�ͨ��֮������ݴ���
 * transferFrom()
 * transferTo()
 * 
 * �塢��ɢ��Scatter����ۼ���Gather��
 * ��ɢ��ȡ��Scattering Reads��:��ͨ���е����ݷ�ɢ�������������
 * �ۼ�д�루Gathering Writes��:������������е����ݾۼ���ͨ����
 * 
 * �����ַ�����Charset
 * ���룺�ַ���->�ֽ�����
 * ���룺�ֽ�����->�ַ���
 * **/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class TestChannal {
	// �ַ���
	@Test
	public void test6() throws Exception {
		Charset cs1 = Charset.forName("GBK");
		
		// ��ȡ������
		CharsetEncoder ce = cs1.newEncoder();
		// �������
		CharsetDecoder cd = cs1.newDecoder();

		CharBuffer cBuffer = CharBuffer.allocate(1024);
		cBuffer.put("����");
		cBuffer.flip();
		// ����
		ByteBuffer bbuf = ce.encode(cBuffer);

		for (int i = 0; i < 4; i++) {
			System.out.println(bbuf.get());
		}
		
		//����
		bbuf.flip();
		CharBuffer cbf = cd.decode(bbuf);
		System.out.println(cbf.toString());
		System.out.println("*******************************");
		bbuf.flip();
		CharBuffer cb2 = Charset.forName("UTF-8").decode(bbuf);
		System.out.println(cb2.toString());
	}

	@Test
	public void test5() {
		Map<String, Charset> map = Charset.availableCharsets();
		Set<Entry<String, Charset>> set = map.entrySet();
		for (Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}

	// ��ɢ�;ۼ�
	@Test
	public void test4() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile("1.jpg", "rw");
		// 1.��ȡͨ��
		FileChannel channel1 = raf1.getChannel();
		// 2.����ָ����С�Ļ�����
		ByteBuffer bf1 = ByteBuffer.allocate(100);
		ByteBuffer bf2 = ByteBuffer.allocate(1024000);
		// 3.��ɢ��ȡ
		ByteBuffer[] buss = { bf1, bf2 };
		channel1.read(buss);

		for (ByteBuffer byteBuffer : buss) {
			byteBuffer.flip();
		}
		System.out.println(new String(buss[0].array(), 0, buss[0].limit()));
		System.out.println("*******************************************");
		System.out.println(new String(buss[1].array(), 0, buss[1].limit()));

		// 4.�ۼ�д��
		RandomAccessFile raf2 = new RandomAccessFile("2.jpg", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(buss);

	}

	// ͨ��֮������ݴ���(ֱ�ӻ�����)
	@Test
	public void test3() throws Exception {
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

		// inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());

		inChannel.close();
		outChannel.close();
	}

	// ʹ��ֱ�ӻ���������ļ��ĸ���(�ڴ�ӳ���ļ�)
	@Test
	public void test2() throws Exception {
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("f:/1.rar"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("f:/2.rar"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

		// �ڴ�ӳ���ļ�
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

		// ֱ�ӶԻ������������ݵĶ�д����
		byte[] dst = new byte[inMappedBuf.limit()];
		inMappedBuf.get(dst);
		outMappedBuf.put(dst);
		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("�ķ�ʱ��Ϊ" + (end - start));
	}

	// ����ͨ������ļ��ĸ���(��ֱ�ӻ�����)
	@Test
	public void test1() throws IOException {

		long start = System.currentTimeMillis();

		FileInputStream fis = null;
		FileOutputStream fos = null;
		// �ٻ�ȡͨ��
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("f:/1.rar");
			fos = new FileOutputStream("f:/2.rar");

			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			// �ڷ���ָ����С�Ļ�����
			ByteBuffer buf = ByteBuffer.allocate(1024);

			// �۽�ͨ���е����ݴ��뻺������
			while (inChannel.read(buf) != -1) {
				buf.flip();// �л��̶�ȡ���ݵ�ģʽ
				// �ܽ��������е�����д��ͨ��
				outChannel.write(buf);
				buf.clear();// ��ջ�����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		long end = System.currentTimeMillis();
		System.out.println("�ķ�ʱ��Ϊ" + (end - start));
	}
}
