package controller;

import model.Address;
import model.Employee;

public class LoginController {
	private static Employee currEmployee = new Employee("Joe", "Biden", new Address("street", "house", "zip", "city"), "12345678", "joeb@", "Receptionist");
	
	public Employee getCurrEmployee() {
		return currEmployee;
	}
}
