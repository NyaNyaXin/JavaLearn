package com.cx.java8.optional;

public class Godness {
	private String name;

	public Godness() {
	}

	public Godness(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Godness [name=" + name + "]";
	}
	
}
