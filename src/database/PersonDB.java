package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnection;
import database.DataAccessException;
import model.Employee;
import model.Guest;

public class PersonDB implements PersonDAO {
	private static final String ADD_ZIPCITY_TO_DB_Q = "insert into ZipCity values (?, ?)";
	private static final String ADD_ADDRESS_TO_DB_Q = "insert into Address values (?, ?, ?)";
	private static final String ADD_PERSON_TO_DB_Q = "insert into Person values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_GUEST_TO_DB_Q = "insert into Guest values (?, ?, ?)";
	
	private PreparedStatement addZipCityToDB, addAddressToDB, addPersonToDB, addGuestToDB;
	
	public PersonDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			this.addZipCityToDB = con.prepareStatement(ADD_ZIPCITY_TO_DB_Q);
			this.addAddressToDB = con.prepareStatement(ADD_ADDRESS_TO_DB_Q);
			this.addPersonToDB = con.prepareStatement(ADD_PERSON_TO_DB_Q);
			this.addGuestToDB = con.prepareStatement(ADD_GUEST_TO_DB_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Statements could not be prepared", e);
		}
	}
	
	@Override
	public boolean addGuestToDB(Guest guest) {
		boolean res = false;
		
		
		
		return res;
	}
	
	
	
	
	
	
	@Override
	public Employee findEmployeeByEmail(String email) {
		return null;
		//TODO Employee is currently handled by LoginController
	}
}
