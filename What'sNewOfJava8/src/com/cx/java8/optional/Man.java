package com.cx.java8.optional;

public class Man {
	private Godness godness;

	public Man() {
	}

	public Man(Godness godness) {
		this.godness = godness;
	}

	/**
	 * @return the godness
	 */
	public Godness getGodness() {
		return godness;
	}

	/**
	 * @param godness the godness to set
	 */
	public void setGodness(Godness godness) {
		this.godness = godness;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Man [godness=" + godness + "]";
	}
	
}
