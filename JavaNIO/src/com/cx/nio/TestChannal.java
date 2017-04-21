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
 * 
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）:将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）:将多个缓冲区中的数据聚集到通道中
 * 
 * 六、字符集：Charset
 * 编码：字符串->字节数组
 * 解码：字节数组->字符串
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
	// 字符集
	@Test
	public void test6() throws Exception {
		Charset cs1 = Charset.forName("GBK");
		
		// 获取编码器
		CharsetEncoder ce = cs1.newEncoder();
		// 与解码器
		CharsetDecoder cd = cs1.newDecoder();

		CharBuffer cBuffer = CharBuffer.allocate(1024);
		cBuffer.put("称心");
		cBuffer.flip();
		// 编码
		ByteBuffer bbuf = ce.encode(cBuffer);

		for (int i = 0; i < 4; i++) {
			System.out.println(bbuf.get());
		}
		
		//解码
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

	// 分散和聚集
	@Test
	public void test4() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile("1.jpg", "rw");
		// 1.获取通道
		FileChannel channel1 = raf1.getChannel();
		// 2.分配指定大小的缓冲区
		ByteBuffer bf1 = ByteBuffer.allocate(100);
		ByteBuffer bf2 = ByteBuffer.allocate(1024000);
		// 3.分散读取
		ByteBuffer[] buss = { bf1, bf2 };
		channel1.read(buss);

		for (ByteBuffer byteBuffer : buss) {
			byteBuffer.flip();
		}
		System.out.println(new String(buss[0].array(), 0, buss[0].limit()));
		System.out.println("*******************************************");
		System.out.println(new String(buss[1].array(), 0, buss[1].limit()));

		// 4.聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("2.jpg", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(buss);

	}

	// 通道之间的数据传输(直接缓冲区)
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

	// 使用直接缓冲区完成文件的复制(内存映射文件)
	@Test
	public void test2() throws Exception {
		long start = System.currentTimeMillis();
		FileChannel inChannel = FileChannel.open(Paths.get("f:/1.rar"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("f:/2.rar"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

		// 内存映射文件
		MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappedBuf = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

		// 直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappedBuf.limit()];
		inMappedBuf.get(dst);
		outMappedBuf.put(dst);
		inChannel.close();
		outChannel.close();

		long end = System.currentTimeMillis();
		System.out.println("耗费时间为" + (end - start));
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
		long end = System.currentTimeMillis();
		System.out.println("耗费时间为" + (end - start));
	}
}
