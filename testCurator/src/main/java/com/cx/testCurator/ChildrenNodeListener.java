package com.cx.testCurator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ChildrenNodeListener {
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
		//节点监听
		final NodeCache cache = new NodeCache(cura, "/chenxin");
		//开始监听
		cache.start();
		//注册事件监听器
		cache.getListenable().addListener(new NodeCacheListener() {
			
			public void nodeChanged() throws Exception {
				byte[] ret = cache.getCurrentData().getData();
				System.out.println(new String(ret));
			}
		});
		Thread.sleep(Integer.MAX_VALUE);
		}
}
