package com.cx.testzkClient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class SubscribeDataChanges {
	//创建一个类，并实现IZkDataListener接口
	private static class zlDataListener implements IZkDataListener{

		public void handleDataChange(String dataPath, Object data) throws Exception {
			// TODO Auto-generated method stub
			System.out.println(dataPath);
			System.out.println(data.toString());
		}

		public void handleDataDeleted(String dataPath) throws Exception {
			// TODO Auto-generated method stub
			System.out.println(dataPath);
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new BytesPushThroughSerializer());
		System.out.println("conneted ok");
		//订阅数据内容的变化	节点路径,实现了IZkDataListener接口的实例,这里要想看到效果就需要使用另外一个序列化器
		zc.subscribeDataChanges("/node_1", new zlDataListener());
		Thread.sleep(Integer.MAX_VALUE);
	}
}
