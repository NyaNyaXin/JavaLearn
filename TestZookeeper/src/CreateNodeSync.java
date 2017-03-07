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
		//ʵ����Zookeeper���󣬲��������(��������ip:host,��ʱʱ��,ʱ�������[���ﴴ������ȥʵ�ּ������ӿ�])
		zookeeper = new ZooKeeper("192.168.199.129:2181",5000,new CreateNodeSync());
		//��ȡZookeeper��״̬
		System.out.println(zookeeper.getState());
		//zookeeper�������첽���еģ�������Ҫ�����̵߳Ĵ���
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	private void doSomething() {
		//ͬ������
		//�������ݽڵ�(����·��,��������,Ȩ��,����ģʽ[��ʱ/˳��])
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
		//���Watcher���յ����¼�
		System.out.println("�յ��¼�"+event );
		//����zookeeper����
		//�ж������Ƿ�ɹ�
		if(event.getState()==KeeperState.SyncConnected){
			doSomething();
		}
	}
	
}
