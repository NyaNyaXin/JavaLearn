package com.cx.juc2;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoinPool {	
	public static void main(String[] args) {
		Instant start = Instant.now();
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinSumCalculate(0, 1000000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);
		
		Instant end = Instant.now();
		System.out.println("耗费"+Duration.between(start, end).toMillis());
		
	}
	
	@Test
	public void test1(){
		Instant start = Instant.now();
		long sum = 0L;
		for (long i = 0; i <= 100000000L; i++) {
			sum+=i;
		}
		Instant end = Instant.now();
		System.out.println(sum);
		System.out.println("耗费"+Duration.between(start, end).toMillis());
	}
	
	@Test
	public void test2(){
		long sum = LongStream.rangeClosed(0L, 10000000000L).parallel().reduce(0L,Long::sum);
		System.out.println(sum);
	}
}
class ForkJoinSumCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1702429418325819006L;

	private long start;
	private long end;
	
	private static final long THURSHOLD = 1000000L;//临界值
	
	public  ForkJoinSumCalculate(long  start,long end) {
		this.start = start;
		this.end=end;
	}
	@Override
	protected Long compute() {
		long length = end-start;
		if(length<=THURSHOLD){
			long sum=0L;
			for(long i=start;i<=end;i++){
				sum+=i;
			}
			return sum;
		}else{
			long middle = (start+end)/2;
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
			left.fork();//进行拆分，同时压入线程队列
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork();//进行拆分，同时压入线程队列
			return left.join()+right.join();
		}
	}
	
}
