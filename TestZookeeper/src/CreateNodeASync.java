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
		//ʵ����Zookeeper���󣬲��������(��������ip:host,��ʱʱ��,ʱ�������[���ﴴ������ȥʵ�ּ������ӿ�])
		zookeeper = new ZooKeeper("192.168.199.129:2181",5000,new CreateNodeASync());
		//��ȡZookeeper��״̬
		System.out.println(zookeeper.getState());
		//zookeeper�������첽���еģ�������Ҫ�����̵߳Ĵ���
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	private void doSomething() {
		//�첽�������첽�����޷���
		//�������ݽڵ�(����·��,��������,Ȩ��,����ģʽ[��ʱ/˳��],StringCallback�ӿ�ʵ��,����[��Ϊ�첽���õ������Ĵ��뵽�ص�������])
		zookeeper.create("/node_3", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new IStringCallback(),"����");

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
	
	static class IStringCallback implements AsyncCallback.StringCallback{

		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			sb.append("rc"+rc).append("\n");//������
			sb.append("path"+path).append("\n");//·��
			sb.append("ctx"+ctx).append("\n");//����������ʵ�ֵ
			sb.append("name"+name);//�ڵ�����
			System.out.println(sb.toString());
			}
		
	}
}
