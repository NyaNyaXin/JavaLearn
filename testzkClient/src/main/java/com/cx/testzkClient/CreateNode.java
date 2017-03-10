package com.cx.testzkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

public class CreateNode {
	public static void main(String[] args) {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
		User u = new User();
		u.setId(1);
		//创建数据节点   节点路径,节点数据,节点类型  	方法返回成功创建节点的路径
		String path = zc.create("/chenxin233", u, CreateMode.PERSISTENT);
		System.out.println("节点："+path+"创建成功");
	}
}
