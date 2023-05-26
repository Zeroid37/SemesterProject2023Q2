package database;

import java.time.LocalDate;
import java.util.List;

import model.Apartment;

public class ApartmentDB implements ApartmentDAO {

	@Override
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd, double minPrice, double maxPrice,
			String apartmentType, int noOfBeds, int floorNo, boolean hasBalcony) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment findApartmentByApartmentNo(String apartmentNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
