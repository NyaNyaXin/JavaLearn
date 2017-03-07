import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;


public class CreateNodeSyncAuth implements Watcher { 

	private static ZooKeeper zookeeper;
	//��ֹ�����ظ�ִ�����跽����Ҫ���� somethingDone����һ��boolean����=false
	private static boolean somethingDone = false;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		zookeeper = new ZooKeeper("192.168.199.129:2181",5000,new CreateNodeSyncAuth());
		System.out.println(zookeeper.getState());
		
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	/*
	 * Ȩ��ģʽ(scheme): ip, digest
	 * ��Ȩ����(ID)
	 * 		ipȨ��ģʽ:  �����ip��ַ
	 * 		digestȨ��ģʽ: username:Base64(SHA-1(username:password))
	 * Ȩ��(permission): create(C), DELETE(D),READ(R), WRITE(W), ADMIN(A) 
	 * 		ע������Ȩ�ޣ���ȫȨ�ޣ�����Ȩ��
	 * 
	 * Ȩ�����: scheme + ID + permission
	 * 
	 * 
	 * 
	 * */
	
	private void doSomething(){
		try {
			//����Ȩ��(����IP��ַ)	//Ȩ��			//����(ip),ip��ַ
			ACL aclIp = new ACL(Perms.READ,new Id("ip","192.168.199.129"));
			//�����û���������		//Ȩ��							//����(�û���)//�������ɸ����ַ����ķ���("�û���:����")		
			ACL aclDigest = new ACL(Perms.READ|Perms.WRITE,new Id("digest",DigestAuthenticationProvider.generateDigest("chen:1234")));
			//��Ȩ����Ϸ��뵽����֮��
			ArrayList<ACL> acls = new ArrayList<ACL>();
			acls.add(aclDigest);
			acls.add(aclIp);
			//zookeeper.addAuthInfo("digest", "jike:123456".getBytes());
			//�����ڵ��ǽ�������ΪȨ����Ϣ����
			String path = zookeeper.create("/node_4", "123".getBytes(), acls, CreateMode.PERSISTENT);
			System.out.println("return path:"+path);
			//ִ��������¸�ֵ
			somethingDone = true;
			
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("�յ��¼���"+event);
		if (event.getState()==KeeperState.SyncConnected){
			//�¼���������somethingDone��ֵ,��������ߺ��ظ�ִ�е��������
			if (!somethingDone && event.getType()==EventType.None && null==event.getPath()){
				doSomething();
			}
		}
	}
	
}
