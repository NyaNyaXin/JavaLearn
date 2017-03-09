package com.cx.testCurator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CheckExists {
	public static void main(String[] args) throws Exception {
		//优化系统性能，创建线程池,并将线程池传递给异步调用
		ExecutorService es = Executors.newFixedThreadPool(5);
		
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
		//判断节点是否存在	如果节点存在会返回一个stat对象，如果不存在会返回空
		cura.checkExists().inBackground(new BackgroundCallback() {
			//加上.inBackground()并实现BackgroundCallback接口，即可实现异步调用,无返回值
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//				CuratorEventType t = event.getType();//获取事件类型
//				int r = event.getResultCode();//获取事件返回码 //0成功
//				Object o = event.getContext();//获取异步调用上下文
//				event.getPath();
//				event.getChildren();
//				event.getData();
				Stat stat = new Stat();
				System.out.println(stat);
			}
		},"123",es).forPath("/chenxin");
		
		Thread.sleep(Integer.MAX_VALUE);
		es.shutdown();
	}
}
