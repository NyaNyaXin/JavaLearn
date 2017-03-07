

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetDataASync implements Watcher{
	
	
    private static ZooKeeper zooKeeper;
    
    
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		
		zooKeeper = new ZooKeeper("192.168.199.129:2181",5000,new GetDataASync());
		System.out.println(zooKeeper.getState().toString());	
		
		Thread.sleep(Integer.MAX_VALUE);
		
		
		

	}
	
	private void doSomething(ZooKeeper zookeeper){

		//异步调用回掉接口	
		zooKeeper.getData("/node_1", true, new IDataCallback(), null);	
	
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub

		if (event.getState()==KeeperState.SyncConnected){
			if (event.getType()==EventType.None && null==event.getPath()){
				doSomething(zooKeeper);
			}else{
				if (event.getType()==EventType.NodeDataChanged){
					try {
						zooKeeper.getData(event.getPath(), true, new IDataCallback(), null);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		
		}
	}
	//实现DataCallback
	static class IDataCallback implements AsyncCallback.DataCallback{

		@Override
		public void processResult(int rc, String path, Object ctx, byte[] data,
				Stat stat) {
			try {
				System.out.println(new String(zooKeeper.getData(path, true, stat)));
				System.out.println("stat:"+stat);
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}
