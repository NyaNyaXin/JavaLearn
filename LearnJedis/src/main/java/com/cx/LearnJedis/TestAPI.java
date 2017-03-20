package com.cx.LearnJedis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.199.129", 6379);
		jedis.auth("chenxin");
		jedis.set("chen", "xin");
		jedis.set("a", "huihui");
		
	}
}
