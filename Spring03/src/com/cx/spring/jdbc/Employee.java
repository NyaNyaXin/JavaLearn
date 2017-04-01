package com.cx.spring.jdbc;

public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	private Integer deptid;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the deptid
	 */
	public Integer getDeptid() {
		return deptid;
	}

	/**
	 * @param deptid the deptid to set
	 */
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", deptid=" + deptid + "]";
	}

	
	

}
