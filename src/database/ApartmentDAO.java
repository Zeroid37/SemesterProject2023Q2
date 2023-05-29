package database;
import java.time.LocalDate;
import java.util.List;

import model.Apartment;

public interface ApartmentDAO {
	public List<Apartment> searchForApartments(double minPrice, double maxPrice, String apartmentType, 
											   int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException;
	public Apartment findApartmentByApartmentNo(String apartmentNo) throws DataAccessException;
}
