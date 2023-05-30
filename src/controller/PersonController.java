package controller;

import database.*;
import model.*;

public class PersonController {
	
	/**
	 * Creates a Guest object and utilizes transactions to add the guest to the Database.
	 * @param	firstName	First name of the guest
	 * @param	familyName	Family name of the guest(s)
	 * @param 	street		The street name the guest lives on
	 * @param	houseNo		The house number the guest lives on
	 * @param 	zip			The zipcode the guest lives in
	 * @param	city		The city the guest lives in
	 * @param 	phone		The phone number of the guest
	 * @param	email		The email of the guest
	 * @param 	country		The country the guest lives in
	 * @param	type		The type of Person they are
	 * @return Guest object
	 */
	public Guest createGuest(String firstName, String familyName, String street, String houseNo,
			                 String zip, String city, String phone, String email, String country, String type) throws DataAccessException {
		
		Address address = new Address(street, houseNo, zip, city);
		Guest guest = new Guest(firstName, familyName, address, phone, email, country);
		PersonDAO personDAO = new PersonDB();
		
		try {
			DBConnection.getInstance().startTransaction();
			personDAO.addGuestToDB(guest);
			DBConnection.getInstance().commitTransaction();
		} catch (Exception e) {
			DBConnection.getInstance().rollbackTransaction();
		}
		return guest;
	}
}


