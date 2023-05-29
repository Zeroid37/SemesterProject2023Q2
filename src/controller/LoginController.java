package controller;

import model.Address;
import model.Employee;

public class LoginController {
	private static Employee currEmployee = new Employee("Joe", "Biden", new Address("street", "house", "zip", "city"), "12345678", "joeb@", "Receptionist");
	
	/**
	 * Method to get the current employee "logged in". This class is currently a STUB
	 * @return Employee
	 */
	public Employee getCurrEmployee() {
		return currEmployee;
	}
}
