package com.cx.testCurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;

public class DelNode {
	public static void main(String[] args) throws Exception {
		//设置重试策略③		一直重试(重试的最大时间,重试的时间间隔)
		RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
		//风格②Fluent
		CuratorFramework cura =  CuratorFrameworkFactory.builder()
				.connectString("192.168.199.129:2181")
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.build();
		//开始连接
		cura.start();
		System.out.println("starting connection");
		//删除数据节点
		cura.delete()
		//删除节点的保障机制
		.guaranteed()
		//是否删除子节点
		.deletingChildrenIfNeeded()
		//指定版本号
		.withVersion(-1)
		//单节点删除
		.forPath("/node_2");
		System.out.println("s删除成功");
		Thread.sleep(Integer.MAX_VALUE);
	}
}
