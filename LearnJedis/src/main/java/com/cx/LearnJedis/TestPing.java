package com.cx.LearnJedis;
import redis.clients.jedis.Jedis;

public class TestPing {
	public static void main(String[] args) {
		//jedis连接redis
		Jedis jedis = new Jedis("192.168.199.129",6379);
		//授权
		jedis.auth("chenxin");
		jedis.close();
		System.out.println(jedis.ping());
	}
}
