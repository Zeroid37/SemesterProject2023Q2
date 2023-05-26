package controller;

import java.time.LocalDate;
import java.util.List;

import database.*;
import model.*;

public class BookingController {
	private Booking cBooking;
	
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd,
			   								   double minPrice, double maxPrice, String apartmentType,
			   								   int noOfBeds, int floorNo, boolean hasBalcony) {
		ApartmentController apartmentController = new ApartmentController();
		return apartmentController.searchForApartments(dateStart, dateEnd, minPrice, maxPrice, apartmentType, noOfBeds, floorNo, hasBalcony);
	}
	
	public void startBooking(LocalDate dateStart, LocalDate dateEnd, Apartment apartment) {
		int noOfNights = calculateNoOfNights(dateStart, dateEnd);
		LoginController loginController = new LoginController();
		Employee e = loginController.getCurrEmployee();
		cBooking = new Booking(apartment, dateStart, noOfNights);
	}
	
	public Guest createGuest(String firstName, String familyName, String street, String houseNo,
                             String zip, String city, String phone, String email, String country) {
		PersonController personController = new PersonController();
		Guest g = personController.createGuest(firstName, familyName, street, houseNo, zip, city, phone, email, country);
		cBooking.setGuest(g);
		return g;
	}
	
	public boolean bookingConfirm() {
		BookingDAO bookingDAO = new BookingDB();
		return bookingDAO.addBookingToDB(cBooking);
	}
	
	public boolean payDeposit(double amount, String bookingNo) {
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
