import java.security.NoSuchAlgorithmException;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;


public class CreateAuthString {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		//调用密文生成函数，获取加密过的密码
		System.out.println(DigestAuthenticationProvider.generateDigest("jike:123456"));
	}
}
