package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.*;
import model.*;

public class BookingController {
	private Booking cBooking;

	/**
	 * Sweeps database for Apartments with the specified criteria, builds the
	 * objects and returns back a list of the apartments matching the criteria.
	 * Thereafter checks each apartment to see if they are available at the given
	 * dateStart and dateEnd By comparing the time period with every booking the
	 * apartment has.
	 * 
	 * @param dateStart     Start date of possible booking
	 * @param dateEnd       End date of possible booking
	 * @param minPrice      Minimum price for apartment
	 * @param maxPrice      Maximum price for apartment
	 * @param apartmentType Apartment type, e.g "Single" or "Family"
	 * @param noOfBeds      Number of Beds in apartment
	 * @param floorNo       Floor number of apartment
	 * @param hasBalcony    If apartment has a balcony
	 * @return ArrayList of Apartments eligible to be booked in given time period
	 */
	public List<Apartment> searchForApartments(LocalDate dateStart, LocalDate dateEnd, double minPrice, double maxPrice,
			String apartmentType, int noOfBeds, int floorNo, boolean hasBalcony) throws DataAccessException {
		ApartmentController apartmentController = new ApartmentController();
		List<Apartment> apartments = apartmentController.searchForApartments(minPrice, maxPrice, apartmentType,
				noOfBeds, floorNo, hasBalcony);

		List<Booking> bookings = new ArrayList<>();
		BookingDAO bookingDAO = new BookingDB();

		for (int i = 0; i < apartments.size(); i++) {
			Apartment a = apartments.get(i);
			bookings = bookingDAO.findBookingsByApartmentNo(a.getApartmentNo());
			if (bookings.size() > 0) {
				for (int x = 0; x < bookings.size(); x++) {
					if (checkAvailable(dateStart, dateEnd, bookings.get(x))) {
						apartments.remove(i);
						i--;
					}
				}

			}
		}
		return apartments;
	}

	public boolean checkSingleApartment(LocalDate dateStart, LocalDate dateEnd, String apartmentNo) throws DataAccessException {
		boolean res = true;
		
		List<Booking> bookings = new ArrayList<>();
		BookingDAO bookingDAO = new BookingDB();
		bookings = bookingDAO.findBookingsByApartmentNo(apartmentNo);
		
		for(int i = 0; i < bookings.size() && res; i++) {
			res = checkAvailable(dateStart, dateEnd, bookings.get(i));
		}
		return res;
	}
	
	/**
	 * Logical calculations based on 2 booking periods, to see if there is overlap
	 * and thereby deeming the booking not possible.
	 * 
	 * @param dateStart Start date of the apartment booking
	 * @param dateEnd   End date of the apartment booking
	 * @param a         Apartment object
	 * @return Boolean true or false.
	 */
	private boolean checkAvailable(LocalDate dateStart, LocalDate dateEnd, Booking b) throws DataAccessException {
		boolean res = true;

		int s1 = convertDateToInt(dateStart);
		int e1 = convertDateToInt(dateEnd);

		int s2 = convertDateToInt(b.getDateStart());
		int e2 = s2 + b.getNoOfNights();
	
		if (s1 <= e2 && s2 >= e1 || s1 >= e2 && s2 <= e1) {
			res = false;
		}

		return res;
	}

	/**
	 * Method for beginning the booking process. Finds apartment by its apartment
	 * number, calculates number of nights, sets a price and sets the employee to
	 * the booking
	 * 
	 * @param dateStart   Start date of the booking
	 * @param dateEnd     End date of the booking
	 * @param apartmentNo Apartment number of the apartment that wishes to be booked
	 * @return Booking as cBooking
	 */
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

	/**
	 * Creates a Guest object by calling it's constructor, and sets the Guest to
	 * cBooking(Current booking)
	 * 
	 * @param firstName  First name of the guest
	 * @param familyName Family name of the guest(s)
	 * @param street     The street name the guest lives on
	 * @param houseNo    The house number the guest lives on
	 * @param zip        The zipcode the guest lives in
	 * @param city       The city the guest lives in
	 * @param phone      The phone number of the guest
	 * @param email      The email of the guest
	 * @param country    The country the guest lives in
	 * @return Guest object
	 */
	public Guest createGuest(String firstName, String familyName, String street, String houseNo, String zip,
			String city, String phone, String email, String country) throws DataAccessException {

		PersonController personController = new PersonController();
		Guest g = personController.createGuest(firstName, familyName, street, houseNo, zip, city, phone, email, country,
				"g");
		cBooking.setGuest(g);
		return g;
	}

	/**
	 * Confirms the booking and utilizes transactions to add the booking to the
	 * database
	 * 
	 * @return Boolean whether it succeeded or not
	 */
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

	/**
	 * Method to pay the deposit of the booking, after it has been confirmed.
	 * 
	 * @param amount    The amount the Guest have paid, should exceed at minimum 50%
	 *                  of the total price
	 * @param bookingNo the booking number of the booking the guest wishes to pay
	 *                  deposit to
	 * @return Boolean true or false if the amount is enough to pay the deposit
	 */
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

	/**
	 * Logical calculation to determine number of nights based on a date start and
	 * date end
	 * 
	 * @param dateStart Start date
	 * @param dateEnd   End date
	 * @return number of nights in int
	 */
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

	/**
	 * Logical calculations to convert a date into days since year 0
	 * 
	 * @param date date objects
	 * @return int of the amount of days the date totals to
	 */
	private int convertDateToInt(LocalDate date) {
		int year = date.getYear();
		int day = date.getDayOfYear();
		int total = year * 365 + day;
		return total;
	}
}
