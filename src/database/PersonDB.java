package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnection;
import database.DataAccessException;
import model.Address;
import model.Employee;
import model.Guest;

public class PersonDB implements PersonDAO {
	private static final String FIND_GUEST_BY_GUEST_NO_Q = "select firstName, famName, phone, email, userID, password, isAdmin, Guest.country, Guest.guestNo, Address.street, Address.houseno, ZipCity.zip, ZipCity.city from Person "
			+ "INNER JOIN Guest on Guest.email_FK = Person.email "
			+ "INNER JOIN Address on person.addressId_FK = address.id "
			+ "INNER JOIN ZipCity on Address.zip_FK = zipCity.zip "
			+ "where guestNo = ? and type = ?";
	
	private static final String ADD_ZIPCITY_TO_DB_Q = "insert into ZipCity values (?, ?)";
	private static final String ADD_ADDRESS_TO_DB_Q = "insert into Address values (?, ?, ?)";
	private static final String ADD_PERSON_TO_DB_Q = "insert into Person values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_GUEST_TO_DB_Q = "insert into Guest values (?, ?, ?)";
	
	private PreparedStatement findGuestByGuestNo, addZipCityToDB, addAddressToDB, addPersonToDB, addGuestToDB;
	
	public PersonDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			this.findGuestByGuestNo = con.prepareStatement(FIND_GUEST_BY_GUEST_NO_Q);
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
	public Guest findGuestByGuestNo(int guestNo) {
		Guest g = null;
		
		try {
			this.findGuestByGuestNo.setInt(1, guestNo);
			this.findGuestByGuestNo.setString(2, "g");
			ResultSet rsApartment = this.findGuestByGuestNo.executeQuery();
			
			if(rsApartment.next()) {
				g = buildGuest(rsApartment);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in ApartmentDB.findApartmentByApartmentNo()");
		}
		return g;
	}
	
	
	private Guest buildGuest(ResultSet rs) {
		Guest g = null;
		Address a = null;
		
		try {
			if(rs.next()) {
				String street = rs.getString("street");
				String houseNo = rs.getString("houseno");
				String zip = rs.getString("zip");
				String city = rs.getString("city");
				
				a = new Address(street, houseNo, zip, city);
				
				String firstName = rs.getString("firstName");
				String famName = rs.getString("famName");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String userID = rs.getString("userID");
				String password = rs.getString("password");
				boolean isAdmin = rs.getBoolean("isAdmin");
				String country = rs.getString("country");
				int guestNo = rs.getInt("guestNo");
				
				g = new Guest(firstName, famName, a, phone, email, userID, password, isAdmin, country, guestNo);
			}
		} catch (Exception e) {
			
		}
		
		return g;
	}
	
	@Override
	public Employee findEmployeeByEmail(String email) {
		return null;
		//TODO Employee is currently handled by LoginController
	}
	
}
