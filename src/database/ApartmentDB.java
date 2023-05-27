package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import database.DataAccessException;
import model.Apartment;

public class ApartmentDB implements ApartmentDAO {
	private static final String FIND_BY_CRITERIA = "select Apartment.apartmentNo, Apartment.apartmentType, hasBalcony, floorNo, numberOfBeds, viewDescription, price from ApartmentDescription "
			+ "INNER JOIN Apartment ON Apartment.apartmentDescription_FK = ApartmentDescription.id "
			+ "INNER JOIN Price ON price.apartmentDescription_FK = ApartmentDescription.id "
			+ "where apartmentType = ? and numberOfBeds = ? and floorNo = ? and hasBalcony = ? and price between ? and ? ";

	private PreparedStatement findByCriteria;

	public ApartmentDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();

			findByCriteria = con.prepareStatement(FIND_BY_CRITERIA);

		} catch (SQLException e) {
			throw new DataAccessException("Something went wrong", e);
		}
	}

	@Override
	public List<Apartment> searchForApartments(double minPrice, double maxPrice, String apartmentType, int noOfBeds,
			int floorNo, boolean hasBalcony) {

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
		} catch (Exception e) {

		}

		return null;
	}

	
	private Apartment buildObject(ResultSet rsAparment) {
		Apartment a = null;
		
		try {
			if(rsAparment.next()) {
				String apartmentNo = rsAparment.getString("apartmentNo");
				String apartmentType = rsAparment.getString("apartmentType");
				boolean hasBalcony = rsAparment.getBoolean("hasBalcony");
				String floorNo = rsAparment.getString("floorNo");
				int noOfBeds = rsAparment.getInt("numberOfBeds");
				String viewDescription = rsAparment.getString("viewDescription");
				double pricePerNight = rsAparment.getDouble("price");
				
				a = new Apartment(apartmentNo, apartmentType, hasBalcony, floorNo, noOfBeds, viewDescription, pricePerNight);
			}
		} catch (Exception e) {
			
		}
		return a;
		
	}
	
	
	@Override
	public Apartment findApartmentByApartmentNo(String apartmentNo) {
		return null;
	}

}
