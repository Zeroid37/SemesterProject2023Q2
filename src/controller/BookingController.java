package controller;

import java.time.LocalDate;
import java.util.List;

import database.*;
import model.*;

public class BookingController {
	private Booking cBooking;
	
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd,
			   								   double minPrice, double maxPrice, String apartmentType,
			   								   int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException {
		ApartmentController apartmentController = new ApartmentController();
		List<Apartment> apartments = apartmentController.searchForApartments(minPrice, maxPrice, apartmentType, noOfBeds, floorNo, hasBalcony);
		
		return apartments;
	}
	
	public Booking startBooking(LocalDate dateStart, LocalDate dateEnd, String apartmentNo) throws DataAccessException {
		ApartmentController apartmentController = new ApartmentController();
		Apartment apartment = apartmentController.findApartmentByAparmentNo(apartmentNo);
		
		int noOfNights = calculateNoOfNights(dateStart, dateEnd);
		cBooking = new Booking(apartment, dateStart, noOfNights);
		cBooking.calculateAndSetPrice();
		
		LoginController loginController = new LoginController();
		Employee e = loginController.getCurrEmployee();
		cBooking.setEmployee(e);
		return cBooking;
	}
	
	public Guest createGuest(String firstName, String familyName, String street, String houseNo,
                             String zip, String city, String phone, String email, String country) throws DataAccessException {
		PersonController personController = new PersonController();
		Guest g = personController.createGuest(firstName, familyName, street, houseNo, zip, city, phone, email, country);
		cBooking.setGuest(g);
		return g;
	}
	
	public boolean bookingConfirm() throws DataAccessException {
		boolean res = false;
		try {
		DBConnection.getInstance().startTransaction();
		BookingDAO bookingDAO = new BookingDB();
		res = bookingDAO.addBookingToDB(cBooking);
		DBConnection.getInstance().commitTransaction();
		} catch (Exception e) {
			DBConnection.getInstance().rollbackTransaction();
		}
		return res;
	}
	
	public boolean payDeposit(double amount, String bookingNo) throws DataAccessException {
		BookingDAO bookingDAO = new BookingDB();
		Booking booking = bookingDAO.findBookingByBookingNo(bookingNo);
		boolean res = booking.isDepositValid(amount);
		if(res) {
			booking.setDepositPaid(true);
			bookingDAO.updateBookingInDB(booking);
		}
		return res;
	}

	private int calculateNoOfNights(LocalDate dateStart, LocalDate dateEnd) {
		int startDay = dateStart.getDayOfYear();
		int endDay = dateStart.getDayOfYear();
		int startYear = dateStart.getYear();
		int endYear = dateStart.getDayOfYear();
		int res;
		
		if(startYear == endYear) {
			res = endDay - startDay;
		} else {
			int yearDiff = endYear - startYear;
			res = endDay + yearDiff*365 - startDay;
		}
		
		return res;
	}
	
	
	
	
	
	
//	public void checkAvailable(LocalDate startDate, LocalDate endDate, Apartment a) {
//		int yearStart = 2023;
//		int dayOfYearStart = 165;
//		int noOfNights = 20;
//		
//		int totalStart = (yearStart * 365) + dayOfYearStart; //738395
//		int totalEnd = totalStart + noOfNights; //738415
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
