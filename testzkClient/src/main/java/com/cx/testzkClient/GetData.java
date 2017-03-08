package com.cx.testzkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class GetData {
	public static void main(String[] args) {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
		//获取节点的状态信息
		Stat stat = new Stat();
		//获取节点中的数据内容  参数：节点的完整路径,stat状态对象  		这里返回的是User对象
		User u = zc.readData("/chenxin",stat);
		System.out.println("用户:"+u.getId()+",名字："+u.getName());
		System.out.println(stat);
	}
}
