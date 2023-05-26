package controller;

import database.*;
import model.*;

public class PersonController {
	public Employee findEmployeeByEmail(String email) {
		PersonDAO personDAO = new PersonDB();
		Employee employee = personDAO.findEmployeeByEmail(email);
		return employee;
	}
	
	public Guest createGuest(String firstName, String familyName, String street, String houseNo,
			                 String zip, String city, String phone, String email, String country) {
		Address address = new Address(street, houseNo, zip, city);
		Guest guest = new Guest(firstName, familyName, address, phone, email, 0, country);
		PersonDAO personDAO = new PersonDB();
		personDAO.addGuestToDB(guest);
		return guest;
	}
}
