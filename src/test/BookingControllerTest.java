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
	}
	
	@AfterEach
	void cleanUp() {
		List<Apartment> a = null;
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
		//There currently exists a booking(10387) in the database with the following
		//Start date: 2023-12-05 and End date: 2023-12-12(noOfNights = 7)
		//As we don't want to populate the database with data from test cases, we will use
		//this booking as a baseline for our tests.
		
		//This date should pass our checkAvailable and should therefore
		//build an Apartment object
		LocalDate start = LocalDate.of(2022, 12, 10);
		LocalDate end = LocalDate.of(2022, 12, 20);
		
		List<Apartment> a = new ArrayList<>();
		
	}
	@Test
	void bookingAfterOtherBooking() throws DataAccessException {
		
	}
	@Test
	void bookingOverlapOtherBooking() throws DataAccessException {
		
	}
}
