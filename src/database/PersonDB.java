package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Address;
import model.Apartment;
import model.Employee;
import model.Guest;

public class PersonDB implements PersonDAO {
	private static final String ADD_ZIPCITY_TO_DB_Q = "insert into ZipCity values (?, ?)";
	private static final String ADD_ADDRESS_TO_DB_Q = "insert into Address values (?, ?, ?)";
	private static final String ADD_PERSON_TO_DB_Q = "insert into Person values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_GUEST_TO_DB_Q = "insert into Guest values (?, ?, ?)";
	
	private PreparedStatement addZipCityToDB, addAddressToDB, addPersonToDB, addGuestToDB;
	@Override
	public Employee findEmployeeByEmail(String email) {
		return null;
		//TODO Employee is currently handled by LoginController
	}

	@Override
	public boolean addGuestToDB(Guest guest) {
		boolean res = false;
		return res;
	}
	
	private Address buildAddress(ResultSet rs) {
		Address a = null;

		try {
			if (rs.next()) {
				
			}
		} catch (Exception e) {

		}
		return a;
	}
	
	private Guest buildGuest(ResultSet rs) {
		Guest g = null;

		try {
			if (rs.next()) {
				
			}
		} catch (Exception e) {

		}
		return g;
	}
	
	
}
