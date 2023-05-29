package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Apartment;

public class ApartmentDB implements ApartmentDAO {
	private static final String FIND_BY_CRITERIA_Q = "select Apartment.apartmentNo, Apartment.apartmentType, hasBalcony, floorNo, numberOfBeds, viewDescription, price from ApartmentDescription "
			+ "INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id "
			+ "INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id "
			+ "where apartmentType = ? and numberOfBeds = ? and floorNo = ? and hasBalcony = ? and price between ? and ? ";

	private static final String FIND_BY_APARTMENT_NO_Q = "select Apartment.apartmentNo, Apartment.apartmentType, hasBalcony, floorNo, numberOfBeds, viewDescription, price from ApartmentDescription "
			+ "INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id "
			+ "INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id " + "where apartmentNo = ?";

	private PreparedStatement findByCriteria;
	private PreparedStatement findByApartmentNo;

	public ApartmentDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();

			findByCriteria = con.prepareStatement(FIND_BY_CRITERIA_Q);
			findByApartmentNo = con.prepareStatement(FIND_BY_APARTMENT_NO_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Statements could not prepare. (ApartmentDB)", e);
		}
	}

	/**
	 * Database call to search for all eligible apartments that match the parameter criteria
	 * @param	dateStart	Start date of possible booking
	 * @param	dateEnd		End date of possible booking
	 * @param 	minPrice	Minimum price for apartment
	 * @param	maxPrice	Maximum price for apartment
	 * @param 	apartmentType	Apartment type, e.g "Single" or "Family"
	 * @param	noOfBeds	Number of Beds in apartment
	 * @param 	floorNo		Floor number of apartment
	 * @param	hasBalcony	If apartment has a balcony
	 * @return ArrayList of Apartments eligible to be booked in given time period
	 * @throws DataAccessException 
	 */
	@Override
	public List<Apartment> searchForApartments(double minPrice, double maxPrice, String apartmentType, int noOfBeds,
			int floorNo, boolean hasBalcony) throws DataAccessException {

		List<Apartment> apartments = new ArrayList<>();

		try {
			this.findByCriteria.setString(1, apartmentType);
			this.findByCriteria.setInt(2, noOfBeds);
			this.findByCriteria.setInt(3, floorNo);
			this.findByCriteria.setBoolean(4, hasBalcony);
			this.findByCriteria.setDouble(5, minPrice);
			this.findByCriteria.setDouble(6, maxPrice);

			ResultSet rsApartments = this.findByCriteria.executeQuery();

			while (rsApartments.next()) {
				Apartment a = null;
				a = buildObject(rsApartments);
				apartments.add(a);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something went wrong when searching for apartments by criteria.", e);
		}
		return apartments;
	}

	/**
	 * Searches database for an apartment with specified apartment number and builds the object
	 * @param	apartmentNo	Apartment Number
	 * @return Apartment
	 * @throws DataAccessException 
	 */
	@Override
	public Apartment findApartmentByApartmentNo(String apartmentNo) throws DataAccessException {
		Apartment a = null;

		try {
			this.findByApartmentNo.setString(1, apartmentNo);
			ResultSet rsApartment = this.findByApartmentNo.executeQuery();

			if (rsApartment.next()) {
				a = buildObject(rsApartment);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something went wrong when searching for apartments by apartment number.", e);
		}

		return a;
	}
	
	/**
	 * Builds the object based on a resultset given in parameters
	 * @param rsAparment resultset
	 * @return Apartment object
	 */
	private Apartment buildObject(ResultSet rsAparment) throws DataAccessException {
		Apartment a = null;

		try {
			String apartmentNo = rsAparment.getString("apartmentNo");
			String apartmentType = rsAparment.getString("apartmentType");
			boolean hasBalcony = rsAparment.getBoolean("hasBalcony");
			int floorNo = rsAparment.getInt("floorNo");
			int noOfBeds = rsAparment.getInt("numberOfBeds");
			String viewDescription = rsAparment.getString("viewDescription");
			double pricePerNight = rsAparment.getDouble("price");

			a = new Apartment(apartmentNo, apartmentType, hasBalcony, floorNo, noOfBeds, viewDescription,
					pricePerNight);

		} catch (SQLException e) {
			throw new DataAccessException("Something went wrong when building apartment.", e);
		}
		return a;
	}

}
