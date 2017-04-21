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
 * 	2.在jdk1.7中的NIO.2针对各个通道提供了一个静态方法open()
 * 3.在jdk1.7中的NIO.2的Files工具类的newByteChannel()
 * **/
public class TestChannal {

}
