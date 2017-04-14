package com.cx.java8;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		// TODO Auto-generated method stub
		return t.getSalary()>=990;
	}

}
