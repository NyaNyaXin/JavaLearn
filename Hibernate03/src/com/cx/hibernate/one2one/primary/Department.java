package com.cx.hibernate.one2one.primary;

public class Department {
	private Integer deptId;
	private String deptName;
	private Manager mgr;
	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the mgr
	 */
	public Manager getMgr() {
		return mgr;
	}
	/**
	 * @param mgr the mgr to set
	 */
	public void setMgr(Manager mgr) {
		this.mgr = mgr;
	}

	

}
