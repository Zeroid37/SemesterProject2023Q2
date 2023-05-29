package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.lang.model.element.ExecutableElement;

import database.DBConnection;
import database.DataAccessException;
import model.Address;
import model.Employee;
import model.Guest;

public class PersonDB implements PersonDAO {
	private static final String FIND_GUEST_BY_GUEST_NO_Q = "select firstName, famName, phone, email, userID, password, isAdmin, Guest.country, Guest.guestNo, Person.type, Address.street, Address.houseno, ZipCity.zip, ZipCity.city from Person "
			+ "INNER JOIN Guest on Guest.email_FK = Person.email "
			+ "INNER JOIN Address on person.addressId_FK = address.id "
			+ "INNER JOIN ZipCity on Address.zip_FK = zipCity.zip " + "where guestNo = ? and type = ?";

	private static final String ADD_ZIPCITY_TO_DB_Q = "insert into ZipCity values (?, ?)";
	private static final String ADD_ADDRESS_TO_DB_Q = "insert into Address values (?, ?, ?)";
	private static final String ADD_PERSON_TO_DB_Q = "insert into Person values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ADD_GUEST_TO_DB_Q = "insert into Guest values (?, ?, ?)";

	private PreparedStatement findGuestByGuestNo, insertZipCityToDB, insertAddressToDB, insertPersonToDB,
			insertGuestToDB;

	public PersonDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			this.findGuestByGuestNo = con.prepareStatement(FIND_GUEST_BY_GUEST_NO_Q);
			this.insertZipCityToDB = con.prepareStatement(ADD_ZIPCITY_TO_DB_Q);
			this.insertAddressToDB = con.prepareStatement(ADD_ADDRESS_TO_DB_Q, PreparedStatement.RETURN_GENERATED_KEYS);
			this.insertPersonToDB = con.prepareStatement(ADD_PERSON_TO_DB_Q);
			this.insertGuestToDB = con.prepareStatement(ADD_GUEST_TO_DB_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Statements could not be prepared", e);
		}
	}

	/**
	 * Adds a guest object to the database
	 * @param guest Guest object
	 * @return boolean true or false whether it succeeded
	 */
	@Override
	public boolean addGuestToDB(Guest guest) {
		boolean res = false;
		Address a = guest.getAddress();
		try {
			int i = addAddressToDB(a);
			this.insertPersonToDB.setString(1, guest.getFirstName());
			this.insertPersonToDB.setString(2, guest.getFamName());
			this.insertPersonToDB.setInt(3, i);
			this.insertPersonToDB.setString(4, guest.getPhone());
			this.insertPersonToDB.setString(5, guest.getEmail());
			this.insertPersonToDB.setString(6, guest.getUserID());
			this.insertPersonToDB.setString(7, guest.getPassword());
			this.insertPersonToDB.setBoolean(8, guest.isAdmin());
			this.insertPersonToDB.setString(9, guest.getType());
			this.insertPersonToDB.executeUpdate();

			this.insertGuestToDB.setInt(1, guest.getGuestNo());
			this.insertGuestToDB.setString(2, guest.getCountry());
			this.insertGuestToDB.setString(3, guest.getEmail());
			this.insertGuestToDB.executeUpdate();

			res = true;
		} catch (Exception e) {

		}

		return res;
	}
	
	/**
	 * Adds an address object to the database
	 * @param a Address object
	 * @return int surrogate key of the row that was created. Returned int is -1 if failed
	 */
	private int addAddressToDB(Address a) throws DataAccessException {
		int id = -1;
		try {
			this.insertZipCityToDB.setString(1, a.getZip());
			this.insertZipCityToDB.setString(2, a.getCity());
			this.insertZipCityToDB.executeUpdate();

			this.insertAddressToDB.setString(1, a.getStreet());
			this.insertAddressToDB.setString(2, a.getHouseNo());
			this.insertAddressToDB.setString(3, a.getZip());
			
			id = DBConnection.getInstance().executeInsertWithIdentity(insertAddressToDB);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	/**
	 * Find guest by guest number
	 * @param guestNo guest number
	 * @return Guest object
	 */
	@Override
	public Guest findGuestByGuestNo(int guestNo) throws DataAccessException {
		Guest g = null;

		try {
			this.findGuestByGuestNo.setInt(1, guestNo);
			this.findGuestByGuestNo.setString(2, "g");

			ResultSet rs = this.findGuestByGuestNo.executeQuery();

			if (rs.next()) {
				g = buildGuest(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something went wrong in finding guest", e);
		}
		return g;
	}

	/**
	 * Builds guest object based on resultset
	 * @param rs Resultset of a found guest
	 * @return Guest object
	 */
	private Guest buildGuest(ResultSet rs) {
		Guest g = null;
		Address a = null;

		try {
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

		} catch (Exception e) {

		}
		return g;
	}

	@Override
	public Employee findEmployeeByEmail(String email) {
		return null;
		// TODO Employee is currently handled by LoginController
	}

}
