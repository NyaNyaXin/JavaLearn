package com.cx.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

public class TestPipe {
	@Test
	public void test1() throws IOException{
		//1.��ȡ�ܵ�
		Pipe pipe = Pipe.open();
		//2.���������е�����д��ܵ�
		ByteBuffer buf = ByteBuffer.allocate(1024);
		Pipe.SinkChannel sinkChannel= pipe.sink();
		buf.put("ͨ������ܵ���������".getBytes());
		buf.flip();
		sinkChannel.write(buf);
		//3.��ȡ�������е�����
		Pipe.SourceChannel sourceChannel = pipe.source();
		buf.flip();
		sourceChannel.read(buf);
		System.out.println(new String(buf.array(),0,buf.limit()));
		sourceChannel.close();
		sinkChannel.close();
		
		
	}
}
