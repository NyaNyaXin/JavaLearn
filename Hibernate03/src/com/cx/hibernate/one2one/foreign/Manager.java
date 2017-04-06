package com.cx.hibernate.one2one.foreign;

public class Manager {
	private Integer mgrId;
	private String mgrName;
	private Department dept;
	/**
	 * @return the mgrId
	 */
	public Integer getMgrId() {
		return mgrId;
	}
	/**
	 * @param mgrId the mgrId to set
	 */
	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}
	/**
	 * @return the mgrName
	 */
	public String getMgrName() {
		return mgrName;
	}
	/**
	 * @param mgrName the mgrName to set
	 */
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}

}
