package com.cx.java8.streams;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {
	/**
	 * ForkJoin框架
	 **/
	@Test
	public void test1() {
		Instant s = Instant.now();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000000L);
		Long sum = forkJoinPool.invoke(task);
		System.out.println(sum);
		Instant e = Instant.now();
		System.out.println("时间为" + Duration.between(s, e).toMillis());
	}

	// 普通for循环
	@Test
	public void test2() {
		Instant s = Instant.now();
		long sum = 0L;
		for (int i = 0; i <= 10000000000L; i++) {
			sum += i;
		}

		System.out.println(sum);

		Instant e = Instant.now();
		System.out.println("时间为" + Duration.between(s, e).toMillis());
	}
	//java8并行流
	@Test
	public void test3(){
		Instant s = Instant.now();
		LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0,Long::sum);
		Instant e = Instant.now();
		System.out.println("时间为" + Duration.between(s, e).toMillis());
	}
}
