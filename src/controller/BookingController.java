package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.*;
import model.*;

public class BookingController {
	private Booking cBooking;

	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd, double minPrice, double maxPrice,
			String apartmentType, int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException {
		ApartmentController apartmentController = new ApartmentController();
		List<Apartment> apartments = apartmentController.searchForApartments(minPrice, maxPrice, apartmentType,
				noOfBeds, floorNo, hasBalcony);

//		for (int i = 0; i < apartments.size(); i++) {
//			Apartment a = apartments.get(i);
//
//			if (checkAvailable(dateStart, dateEnd, a)) {
//				apartments.remove(i);
//			}
//		}

		return apartments;
	}

	private boolean checkAvailable(LocalDate dateStart, LocalDate dateEnd, Apartment a) throws DataAccessException {
		boolean res = true;
		List<Booking> bookings = new ArrayList<>();

		BookingDAO bookingDAO = new BookingDB();
		bookings = bookingDAO.findBookingsByApartmentNo(a.getApartmentNo());

		int s1 = convertDateToInt(dateStart);
		int e1 = convertDateToInt(dateEnd);

		for (int i = 0; i < bookings.size() && res; i++) {
			int s2 = convertDateToInt(bookings.get(i).getDateStart());
			int e2 = s2 + bookings.get(i).getNoOfNights();

			if (s1 < e2 && s2 < e1) {
				res = false;
			}
		}
		return res;
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
	
	public Guest createGuest(String firstName, String familyName, String street, String houseNo, String zip,
			String city, String phone, String email, String country, String type) throws DataAccessException {
		
		PersonController personController = new PersonController();
		Guest g = personController.createGuest(firstName, familyName, street, houseNo, zip, city, phone, email, country,
				type);
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
		if (res) {
			booking.setDepositPaid(true);
			bookingDAO.updateBookingInDB(booking);
		}
		return res;
	}

	private int calculateNoOfNights(LocalDate dateStart, LocalDate dateEnd) {
		int startDay = dateStart.getDayOfYear();
		int endDay = dateEnd.getDayOfYear();
		int startYear = dateStart.getYear();
		int endYear = dateEnd.getYear();
		int res;

		int yearDiff = endYear - startYear;
		res = endDay + (yearDiff * 365) - startDay;


		return res;
	}

	private int convertDateToInt(LocalDate date) {
		int year = date.getYear();
		int day = date.getDayOfYear();
		int total = year * 365 + day;
		return total;
	}
	
	
}
