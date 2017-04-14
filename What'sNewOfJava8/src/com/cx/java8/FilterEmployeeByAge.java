package com.cx.java8;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		// TODO Auto-generated method stub
		return t.getAge()>=35;
	}

}
