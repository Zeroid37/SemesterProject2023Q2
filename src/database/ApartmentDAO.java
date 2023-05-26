package database;
import java.time.LocalDate;
import java.util.List;

import model.Apartment;

public interface ApartmentDAO {
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd,
								double minPrice, double maxPrice, String apartmentType, 
								int noOfBeds, int floorNo, boolean hasBalcony);
	public Apartment findApartmentByApartmentNo(String apartmentNo);
}
