package com.cx.testzkClient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class NodeDel {
	public static void main(String[] args) {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
		//节点的删除(删除没有子节点的节点)	节点的完整路径		返回一个boolean值
		//boolean e1 = zc.delete("/chenxin");
		//节点的删除(删除带有子节点的节点)	节点的完整路径		返回一个boolean值
		boolean e2 = zc.deleteRecursive("/chenxin");
	}
}
