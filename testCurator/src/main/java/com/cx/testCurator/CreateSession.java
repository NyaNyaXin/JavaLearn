package com.cx.testCurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;

public class CreateSession {
	public static void main(String[] args) throws InterruptedException {
		//设置重试策略①		重试停顿基础时间(单位是ms)，最大重试次数
		//RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		//设置重试策略②		最大重试次数,重试间隔(单位是ms)
		//RetryPolicy retryPolicy = new RetryNTimes(5, 1000);
		//设置重试策略③		一直重试(重试的最大时间,重试的时间间隔)
		RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
//		//创建Curator客户端对象风格①		ip:port,会话超时时间,连接超时时间,重试策略
//		CuratorFramework cura =  CuratorFrameworkFactory.
//				newClient("192.168.199.129:2181",5000,5000, retryPolicy);
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
		Thread.sleep(Integer.MAX_VALUE);
	}
}
