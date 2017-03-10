package com.cx.testzkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class WriteData {
	public static void main(String[] args) {
		//建立连接					        服务器的ip:port,会话过期时间 ,连接超时时间,序列化器(可以处理java对象)
		ZkClient zc = new ZkClient("192.168.199.129:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok");
		
		User u = new User();
		u.setId(2);
		u.setName("hehe");
		
		//更改节点数据	节点路径,节点的新的数据内容,数据版本号
		zc.writeData("/node_1", u,-1 );
		GetData.main(null);
	}
}
