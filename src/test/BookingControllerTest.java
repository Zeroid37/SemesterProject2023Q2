package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import controller.*;
import database.BookingDAO;
import database.BookingDB;
import database.DataAccessException;
import model.Apartment;
import model.Booking;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingControllerTest {
	private BookingController bookingController;
	private LoginController loginController;
	private ApartmentController apartmentController;
	private PersonController personController;
	private BookingDAO bookingDAO;
	private List<Apartment> a;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		bookingController = new BookingController();
		loginController = new LoginController();
		apartmentController = new ApartmentController();
		personController = new PersonController();
		bookingDAO = new BookingDB();
		Booking b = bookingDAO.findBookingByBookingNo("10387");
		b.setDepositPaid(false);
		bookingDAO.updateBookingInDB(b);
		b = null;
		a = new ArrayList<>();
	}

	@AfterEach
	void cleanUp() {
		List<Apartment> a = null;
		Booking b = null;
	}

	@Test
	void isDepositPaidWithRightAmount() throws DataAccessException {
		// There currently exists a booking(10387) in the system without it's deposit
		// paid
		// Without flooding our database with new data every time the test is run
		// we will use that booking as the baseline.
		String bookingNo = "10387";
		Booking booking = bookingDAO.findBookingByBookingNo(bookingNo);

		// The amount that should be paid in the deposit is currently set as 1/2 out of
		// the total price
		double whatWeWillPay = (booking.getPrice() * 0.5);

		// To confirm isDepositPaid is currently false
		assertFalse(booking.isDepositPaid());

		// Now we pay the deposit with the amount specified above
		bookingController.payDeposit(whatWeWillPay, bookingNo);

		// Rebuilding object to check deposit
		booking = null;
		booking = bookingDAO.findBookingByBookingNo(bookingNo);

		// isDepositPaid has now been set to true
		assertTrue(booking.isDepositPaid());
	}

	@Test
	void isDepositPaidWithWrongAmount() throws DataAccessException {
		// There currently exists a booking(10387) in the system without it's deposit
		// paid
		// Without flooding our database with new data every time the test is run
		// we will use that booking as the baseline.
		String bookingNo = "10387";
		Booking booking = bookingDAO.findBookingByBookingNo(bookingNo);

		// The amount that should be paid in the deposit is currently set as 1/2 out of
		// the total price'
		// To deliberately make the method fail, we will give an amount LESS than 1/2 of
		// the total price
		double whatWeWillPay = (booking.getPrice() * 0.4);

		// To confirm isDepositPaid is currently false
		assertFalse(booking.isDepositPaid());

		// Now we pay the deposit with the amount specified above
		bookingController.payDeposit(whatWeWillPay, bookingNo);

		// Rebuilding object to check deposit
		booking = null;
		booking = bookingDAO.findBookingByBookingNo(bookingNo);

		// isDepositPaid is still false
		assertFalse(booking.isDepositPaid());
	}

	@Test
	void bookingBeforeOtherBooking() throws DataAccessException {
		// There currently exists a booking(10387) in the database with the following
		// Start date: 2023-12-05 and End date: 2023-12-12(noOfNights = 7)
		// As we don't want to populate the database with data from test cases, we will
		// use
		// this booking as a baseline for our tests.

		// This date should pass our checkAvailable and should therefore
		// build an Apartment object
		LocalDate start = LocalDate.of(2022, 12, 10);
		LocalDate end = LocalDate.of(2022, 12, 20);

		// These search criteria matches the apartment currently used in the
		// booking(10387) (Trust me bro)
		a = bookingController.searchForApartments(start, end, 499, 501, "Single", 2, 2, true);

		// The method returns an ArrayList, so it is necessary to iterate through it
		// For this search, we know that there will only be returned 1 result
		// Thereby the test shows that the object is created and is not null
		// For further proof that the right object is created
		// The object's data can be weighted with our search criteria
		for(int i=0; i<=a.size()-1; i++) {
			assertNotNull(a.get(i));
			assertTrue(a.get(i).getPricePerNight() > 499 && a.get(i).getPricePerNight() < 501);
			assertTrue(a.get(i).getApartmentType().equals("Single"));
			assertTrue(a.get(i).getFloorNo() == 2);
			assertTrue(a.get(i).getNumberOfBeds() == 2);
			assertTrue(a.get(i).isHasBalcony());
		}
	}

	@Test
	void bookingAfterOtherBooking() throws DataAccessException {
		// There currently exists a booking(10387) in the database with the following
		// Start date: 2023-12-05 and End date: 2023-12-12(noOfNights = 7)
		// As we don't want to populate the database with data from test cases, we will
		// use
		// this booking as a baseline for our tests.

		// This date should pass our checkAvailable and should therefore
		// build an Apartment object
		LocalDate start = LocalDate.of(2024, 12, 10);
		LocalDate end = LocalDate.of(2024, 12, 20);

		// These search criteria matches the apartment currently used in the
		// booking(10387)
		a = bookingController.searchForApartments(start, end, 499, 501, "Single", 2, 2, true);

		// The method returns an ArrayList, so it is necessary to iterate through it
		// For this search, we know that there will only be returned 1 result
		// Thereby the test shows that the object is created and is not null
		// For further proof that the right object is created
		// The object's data can be weighted with our search criteria
		for(int i=0; i<a.size(); i++) {
			assertNotNull(a.get(i));
			assertTrue(a.get(i).getPricePerNight() > 499 && a.get(i).getPricePerNight() < 501);
			assertTrue(a.get(i).getApartmentType().equals("Single"));
			assertTrue(a.get(i).getFloorNo() == 2);
			assertTrue(a.get(i).getNumberOfBeds() == 2);
			assertTrue(a.get(i).isHasBalcony());
		}
	}

	@Test
	void bookingOverlapOtherBooking() throws DataAccessException {
		// There currently exists a booking(10387) in the database with the following
		// Start date: 2023-12-05 and End date: 2023-12-12(noOfNights = 7)
		// As we don't want to populate the database with data from test cases, we will
		// use
		// this booking as a baseline for our tests.

		// This date should pass our checkAvailable and should therefore
		// build an Apartment object
		LocalDate start = LocalDate.of(2022, 12, 10);
		LocalDate end = LocalDate.of(2024, 12, 20);

		// These search criteria matches the apartment currently used in the
		// booking(10387)
		a = bookingController.searchForApartments(start, end, 499, 501, "Single", 2, 2, true);
		System.out.println(a.size());

		// The method returns an ArrayList, so it is necessary to iterate through it
		// For this search, we know that the criteria will result in 0 possible apartments
		//Therefore the list a should have no apartments in it.
		assertTrue(a.size()==0);
	}
}
