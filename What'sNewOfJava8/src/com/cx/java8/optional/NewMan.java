package com.cx.java8.optional;

import java.util.Optional;

public class NewMan {
	private Optional<Godness> godness = Optional.empty();

	public NewMan() {
		super();
	}

	public NewMan(Optional<Godness> godness) {
		super();
		this.godness = godness;
	}

	/**
	 * @return the godness
	 */
	public Optional<Godness> getGodness() {
		return godness;
	}

	/**
	 * @param godness the godness to set
	 */
	public void setGodness(Optional<Godness> godness) {
		this.godness = godness;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewMan [godness=" + godness + "]";
	}
	
}
