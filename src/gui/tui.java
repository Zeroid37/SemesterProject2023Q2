package gui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import controller.BookingController;
import controller.LoginController;
import database.BookingDAO;
import database.BookingDB;
import database.DBConnection;
import database.DataAccessException;
import database.PersonDAO;
import database.PersonDB;
import model.Apartment;
import model.Booking;
import model.Guest;
import model.*;
import controller.*;
import database.*;


public class tui {
	public static void main(String[] args) throws DataAccessException {
		BookingController bookingController = new BookingController();
		
		LocalDate start = LocalDate.of(2023, 12, 11);
		LocalDate end = LocalDate.of(2023, 12, 20);
		
		List<Apartment> a = new ArrayList<>();
		a = bookingController.searchForApartments(start, end, 499, 501, "Single", 2, 2, true);
		
		for(Apartment as : a) {
			System.out.println(as.getApartmentNo());
		}
		
		System.out.println("done");
//		
//		Booking b = bookingController.startBooking(start, end, "3");
//		Guest g = bookingController.createGuest("Niggaman", "Man", "NiggaStreet", "37", "3700", "Detroit", "89312245", "nigga@", "Niggaland");
//		boolean conf = bookingController.bookingConfirm();
//		System.out.println(conf);
//		Boolean paid = bookingController.payDeposit(5000, b.getBookingNo());
//		System.out.println(paid);
//		
//		
//		
//		bookingController.startBooking(start, end, null);
		


//		List<Apartment> aList = new ArrayList<>();
//		aList = bookingController.searchForApartments(LocalDate.now(), LocalDate.now(), 0, 10000, "Single", 2, 2, true);
//		
//		for(Apartment a : aList) {
//			System.out.println(a.getApartmentNo());
//		}
	}
}

