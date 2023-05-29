package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ApartmentDAO;
import database.ApartmentDB;
import database.DataAccessException;
import model.Apartment;

public class ApartmentController {
	
	/**
	 * Sweeps database for Apartments with the specified criteria, 
	 * builds the objects and returns back a list of the apartments matching the criteria.
	 * @param	minPrice	Minimum price of apartment
	 * @param	maxPrice	Maximum price of apartment
	 * @param 	apartmentType	Type of apartment, e.g "Single" or "Family"
	 * @param	noOfBeds	Amount of beds
	 * @param 	floorNo		Floor number
	 * @param	hasBalcony	Whether it has a balcony or not
	 * @return ArrayList of Apartments
	 */
	public List<Apartment> searchForApartments(double minPrice, double maxPrice, String apartmentType, 
											   int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException {
	ApartmentDAO apartmentDAO = new ApartmentDB();
	List<Apartment> aList = new ArrayList<>();
	aList = apartmentDAO.searchForApartments( minPrice, maxPrice, apartmentType, noOfBeds, floorNo, hasBalcony);
	return aList;
}
	/**
	 * Searches database for an apartment with specified apartment number
	 * @param	apartmentNo	Apartment Number
	 * @return Apartment
	 */
	public Apartment findApartmentByAparmentNo(String apartmentNo) throws DataAccessException {
		ApartmentDAO apartmentDAO = new ApartmentDB();
		Apartment a = apartmentDAO.findApartmentByApartmentNo(apartmentNo);
		return a;
	}
}
