package com.cx.testCurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;

public class CreateNode {
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
		//创建数据节点		返回创建数据节点的完整路径
		String path = cura.create()
				//是否创建父节点
				.creatingParentsIfNeeded()
				//创建模式
				.withMode(CreateMode.EPHEMERAL)
				//创建路径数据
				.forPath("/chenxin/1", "123".getBytes());
		System.out.println(path);
		Thread.sleep(Integer.MAX_VALUE);
	}
}
