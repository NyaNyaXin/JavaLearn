package com.cx.testzkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class CreateSession {
	public static void main(String[] args) {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
	}
}
