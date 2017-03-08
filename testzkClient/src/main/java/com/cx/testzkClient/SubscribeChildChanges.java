package com.cx.testzkClient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class SubscribeChildChanges {
	//创建一个类，并实现IZkChildListener接口
	private static class zlChildListener implements IZkChildListener{

		public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
			// TODO Auto-generated method stub
			//输出节点路径
			System.out.println(parentPath);
			//输出子节点列表
			System.out.println(currentChilds.toString());
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
		//订阅子节点的变化	节点路径,实现了IZkChildListener接口的实例
		zc.subscribeChildChanges("/node_1", new zlChildListener());
		Thread.sleep(Integer.MAX_VALUE);
	}
}
