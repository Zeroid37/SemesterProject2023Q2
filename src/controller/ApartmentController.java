package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ApartmentDAO;
import database.ApartmentDB;
import database.DataAccessException;
import model.Apartment;

public class ApartmentController {
	public List<Apartment> searchForApartments(double minPrice, double maxPrice, String apartmentType, 
											   int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException {
	ApartmentDAO apartmentDAO = new ApartmentDB();
	List<Apartment> aList = new ArrayList<>();
	aList = apartmentDAO.searchForApartments( minPrice, maxPrice, apartmentType, noOfBeds, floorNo, hasBalcony);
	return aList;
}
	public Apartment findApartmentByAparmentNo(String apartmentNo) throws DataAccessException {
		ApartmentDAO apartmentDAO = new ApartmentDB();
		Apartment a = apartmentDAO.findApartmentByApartmentNo(apartmentNo);
		return a;
	}
}
