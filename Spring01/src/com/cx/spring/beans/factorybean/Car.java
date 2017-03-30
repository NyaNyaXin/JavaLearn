package com.cx.spring.beans.factorybean;

public class Car {
	private String brand;
	private double price;

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + "]";
	}

	public Car() {
		System.out.println("Car's Constructor.......");
	}

	public Car(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}
	
	

}
