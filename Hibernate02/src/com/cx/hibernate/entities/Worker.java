package com.cx.hibernate.entities;

public class Worker {
	private Integer id;
	private String name;
	private Pay Pay;
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
	/**
	 * @return the pay
	 */
	public Pay getPay() {
		return Pay;
	}
	/**
	 * @param pay the pay to set
	 */
	public void setPay(Pay pay) {
		Pay = pay;
	}


}
