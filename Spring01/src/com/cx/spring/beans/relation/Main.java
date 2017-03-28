package com.cx.spring.beans.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cx.spring.beans.autowire.Address;
import com.cx.spring.beans.autowire.Person;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
//		Address address = (Address) ctx.getBean("address");
//		System.out.println(address);
		
		Address address2 = (Address) ctx.getBean("address2");
		System.out.println(address2);
		
		Person person = (Person) ctx.getBean("person");
		System.out.println(person);
	}
}
