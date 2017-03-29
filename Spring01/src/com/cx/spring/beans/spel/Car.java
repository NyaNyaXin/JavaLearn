package com.cx.spring.beans.spel;

public class Car {
	private String brand;
	private double price;
	//¬÷Ã•÷‹≥§
	private double typePerimeter;
	

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
	
	

	/**
	 * @return the typePerimeter
	 */
	public double getTypePerimeter() {
		return typePerimeter;
	}

	/**
	 * @param typePerimeter the typePerimeter to set
	 */
	public void setTypePerimeter(double typePerimeter) {
		this.typePerimeter = typePerimeter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", typePerimeter=" + typePerimeter + "]";
	}

	public Car() {
		System.out.println("Car's Constructor.......");
	}
	
	

}
