package com.cx.spring.beans.cycle;

public class Car {
	public Car() {
		System.out.println("Car's Contructor......");
	}

	private String brand;

	public void setBrand(String brand) {
		System.out.println("setBrand");
		this.brand = brand;
	}

	public void init() {
		System.out.println("init.....");
	}

	public void destory() {
		System.out.println("destory....");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [brand=" + brand + "]";
	}
	
	
}
