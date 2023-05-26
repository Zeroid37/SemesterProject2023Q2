package model;

import java.util.Random;

public class Employee extends Person {
	private int employeeNo;
	private String department;
	
	public Employee(String firstName, String famName, Address address, String phone, String email, String department) {
		super(firstName, famName, address, phone, email);
		Random rand = new Random();
		this.employeeNo = rand.nextInt(9999);
		this.department = department;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
