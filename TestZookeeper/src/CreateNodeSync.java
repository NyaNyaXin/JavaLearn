import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class CreateNodeSync implements Watcher{
	private static ZooKeeper zookeeper;
	public static void main(String[] args) throws IOException, InterruptedException {
		//实例化Zookeeper对象，并传入参数(服务器的ip:host,超时时间,时间监听器[这里创建新类去实现监听器接口])
		zookeeper = new ZooKeeper("192.168.199.129:2181",5000,new CreateNodeSync());
		//获取Zookeeper的状态
		System.out.println(zookeeper.getState());
		//zookeeper连接是异步进行的，这里需要进行线程的处理
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	private void doSomething() {
		//同步创建
		//创建数据节点(完整路径,数据内容,权限,创建模式[临时/顺序])
		try {
			String path = zookeeper.create("/node_2", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("return path"+path);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do something");
	}
	@Override
	public void process(WatchedEvent event) {
		//输出Watcher接收到的事件
		System.out.println("收到事件"+event );
		//进行zookeeper处理
		//判断连接是否成功
		if(event.getState()==KeeperState.SyncConnected){
			doSomething();
		}
	}
	
}
