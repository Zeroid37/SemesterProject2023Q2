package controller;

import java.time.LocalDate;
import java.util.List;

import database.ApartmentDAO;
import database.ApartmentDB;
import model.Apartment;

public class ApartmentController {
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd,
											   double minPrice, double maxPrice, String apartmentType, 
											   int noOfBeds, int floorNo, boolean hasBalcony) {
	ApartmentDAO apartmentDAO = new ApartmentDB();
	List<Apartment> aList = apartmentDAO.searchForApartments(dateStart, dateEnd, minPrice, maxPrice, apartmentType, noOfBeds, floorNo, hasBalcony);
	return aList;
}
	
}
