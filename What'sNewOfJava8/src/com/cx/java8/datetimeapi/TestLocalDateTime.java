package com.cx.java8.datetimeapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;

import org.junit.Test;

public class TestLocalDateTime {
	// 1.LocalDate LocalTime LocalDateTime

	@Test
	public void test1() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ldt2 = LocalDateTime.of(2016, 10, 19, 13, 22, 33);
		System.out.println(ldt2);

		LocalDateTime ldt3 = ldt.plusYears(2);
		System.out.println(ldt3);

		System.out.println(ldt3.minusMonths(2));

		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonth());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
	}

	// 2.Instant:时间戳(以Unix 元年：1970年1月1日 00:00:00 到某个时间之间的毫秒值)
	@Test
	public void test2() {
		Instant ins = Instant.now();// 默认获取UTC时区
		System.out.println(ins);

		OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);
		System.out.println(ins.toEpochMilli());

		Instant ins2 = Instant.ofEpochSecond(1000);
		System.out.println(ins2);
	}

	// 3.Duration:计算两个时间之间的间隔 	Period:计算两个日期之间的间隔

	@Test
	public void test3() {
		Instant ins1 = Instant.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
		Instant ins2 = Instant.now();
		Duration du = Duration.between(ins1, ins2);
		System.out.println(du.toMillis());
		
		System.out.println("**********************************");
		
		LocalTime lt1 = LocalTime.now();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
		LocalTime lt2 = LocalTime.now();
		System.out.println(Duration.between(lt1, lt2).toMillis());
	}
	
	@Test
	public void test4(){
		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2017, 1, 1);
		Period p = Period.between(ld2, ld1);
		System.out.println(p.getYears());
		System.out.println(p.getMonths());
		System.out.println(p.getDays());
	}
}
