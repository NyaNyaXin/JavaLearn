import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.omg.CORBA.IMP_LIMIT;

public class CreateNodeASync implements Watcher{
	private static ZooKeeper zookeeper;
	public static void main(String[] args) throws IOException, InterruptedException {
		//实例化Zookeeper对象，并传入参数(服务器的ip:host,超时时间,时间监听器[这里创建新类去实现监听器接口])
		zookeeper = new ZooKeeper("192.168.199.129:2181",5000,new CreateNodeASync());
		//获取Zookeeper的状态
		System.out.println(zookeeper.getState());
		//zookeeper连接是异步进行的，这里需要进行线程的处理
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	private void doSomething() {
		//异步创建，异步调用无返回
		//创建数据节点(完整路径,数据内容,权限,创建模式[临时/顺序],StringCallback接口实例,类型[作为异步调用的上下文传入到回调函数中])
		zookeeper.create("/node_3", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new IStringCallback(),"创建");

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
	
	static class IStringCallback implements AsyncCallback.StringCallback{

		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			sb.append("rc"+rc).append("\n");//返回码
			sb.append("path"+path).append("\n");//路径
			sb.append("ctx"+ctx).append("\n");//传入的上下问的值
			sb.append("name"+name);//节点名称
			System.out.println(sb.toString());
			}
		
	}
}
