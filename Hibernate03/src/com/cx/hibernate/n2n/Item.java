package com.cx.hibernate.n2n;

import java.util.HashSet;
import java.util.Set;

public class Item {
	private Integer id;
	private String name;
	private Set<Category> categories = new HashSet<>();
	
	
	/**
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	
}
