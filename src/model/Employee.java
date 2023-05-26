package model;

public class Employee extends Person {
	private int employeeNo;
	private String department;
	
	public Employee(String firstName, String famName, Address address, String phone, String email, int employeeNo, String department) {
		super(firstName, famName, address, phone, email);
		this.employeeNo = employeeNo;
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
