package com.cx.testCurator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class NodeListener {
	public static void main(String[] args) throws Exception {
		// 设置重试策略③ 一直重试(重试的最大时间,重试的时间间隔)
		RetryPolicy retryPolicy = new RetryUntilElapsed(5000, 1000);
		// 风格②Fluent
		CuratorFramework cura = CuratorFrameworkFactory.builder().connectString("192.168.199.129:2181")
				.sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(retryPolicy).build();
		// 开始连接
		cura.start();
		System.out.println("starting connection");
		// 监听子节点 最后一个参数表示是否获取子节点的数据内容
		final PathChildrenCache cache = new PathChildrenCache(cura, "/chenxin", true);
		// 开始监听
		cache.start();
		// 注册事件监听器
		cache.getListenable().addListener(new PathChildrenCacheListener() {

			public void childEvent(CuratorFramework arg0, PathChildrenCacheEvent arg1) throws Exception {
				// TODO Auto-generated method stub
				switch (arg1.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED:"+arg1.getData());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED:"+arg1.getData());
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED:"+arg1.getData());
					break;

				default:
					break;
				}
			}
		});
		Thread.sleep(Integer.MAX_VALUE);
	}
}
