package com.cx.spring.beans.collections;

import java.util.List;
import java.util.Map;

import com.cx.spring.beans.Car;

public class NewPerson {
	private String name;
	private int age;
	private Map<String,Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public NewPerson() {
		super();
	}


	/**
	 * @return the cars
	 */
	public Map<String, Car> getCars() {
		return cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", cars=" + cars + "]";
	}



}
