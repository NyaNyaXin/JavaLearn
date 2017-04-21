package com.cx.nio;
/*
 * 一、通道（Channel）：用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。Channel本身不存储数据，因此需要配合缓冲区进行传输
 * 
 * 二、通道的一些主要实现类
 * 		java.nio.channels.Channel
 * 			|--FileChannel
 * 			|--SocketChannel
 * 			|--ServerSocketChannel
 * 			|--DatagramChannel
 * 
 * 三、获取通道
 * 1.Java针对支持通道的类提供了getChannel()方法
 * 			本地IO
 * 			FileInputStream/FileOutputStream
 * 			RandomAccessFile
 * 			网络IO
 * 			Socket
 * 			Server Socket
 * 			DatagramSocket
 * 2.在jdk1.7中的NIO.2针对各个通道提供了一个静态方法open()
 * 3.在jdk1.7中的NIO.2的Files工具类的newByteChannel()
 * 
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * **/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestChannal {
	// 通道之间的数据传输(直接缓冲区)
	@Test
	public void test3() throws Exception{
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW,StandardOpenOption.READ);
		
		//inChannel.transferTo(0, inChannel.size(), outChannel);
		outChannel.transferFrom(inChannel, 0, inChannel.size());
		
		inChannel.close();
		outChannel.close();
	}
	// 使用直接缓冲区完成文件的复制(内存映射文件)
	@Test
	public void test2() throws Exception{
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("f:/1.rar"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("f:/2.rar"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW,StandardOpenOption.READ);
		
		//内存映射文件
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		//直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappedBuf.limit()];
		inMappedBuf.get(dst);
		outMappedBuf.put(dst);
		inChannel.close();
		outChannel.close();
		
		long end =System.currentTimeMillis();
		System.out.println("耗费时间为"+(end-start));
	}
	
	// 利用通道完成文件的复制(非直接缓冲区)
	@Test
	public void test1() throws IOException {
		
		long start = System.currentTimeMillis();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		// ①获取通道
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			fis = new FileInputStream("f:/1.rar");
			fos = new FileOutputStream("f:/2.rar");

			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			// ②分配指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);

			// ③将通道中的数据存入缓冲区中
			while (inChannel.read(buf) != -1) {
				buf.flip();// 切换程读取数据的模式
				// ④将缓冲区中的数据写入通道
				outChannel.write(buf);
				buf.clear();// 清空缓冲区
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
		long end =System.currentTimeMillis();
		System.out.println("耗费时间为"+(end-start));
	}
}
