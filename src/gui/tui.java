package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import database.BookingDAO;
import database.BookingDB;
import database.DBConnection;
import database.DataAccessException;
import model.Apartment;
import model.Booking;

public class tui {
	public static void main(String[] args) {
		
//		try {
//			DBConnection dbc = DBConnection.getInstance();
//			Connection con = dbc.getConnection();
//			
//			Statement s = con.createStatement();
//			ResultSet rs = s.executeQuery("select * from Apartment");
//			
//			while(rs.next()) {
//				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
//			}
//		} catch (Exception e) {
//			
//		}
		System.out.println(testCheck(150, 155)); //true
		System.out.println(testCheck(155, 165)); //true
		System.out.println(testCheck(150, 166)); //false
		System.out.println(testCheck(130, 160)); //false
		System.out.println(testCheck(166, 169)); //false
		System.out.println(testCheck(170, 185)); //true
		
	}


	private static boolean testCheck(int s1, int e1) { // x1 = s1, x2 = e1, y1= s2, y2 = e2
		boolean res = true;
		ArrayList<Integer> s2List = new ArrayList<>();
		ArrayList<Integer> e2List = new ArrayList<>();

		s2List.add(140);
		e2List.add(150);
		// 15
		s2List.add(165);
		e2List.add(170);
		// 20
		s2List.add(190);
		e2List.add(205);
		
		for (int i = 0; i < s2List.size() && res ; i++) {
			int s2 = s2List.get(i);
			int e2 = e2List.get(i);
			
//			System.out.println(s1 + " <= " + e2 + " && " + e1 + " <= " + s2 + " = " + (s1 <= e2 && e1 <= s2));
			
			if(s1 < e2 && s2 < e1) {
				res = false;
			}
		}
		
		return res;
	}



}
//private boolean checkAvailable(LocalDate dateStart, LocalDate dateEnd, Apartment a) throws DataAccessException {
//	boolean res = true;
//	List<Booking> bookings = new ArrayList<>();
//
//	BookingDAO bookingDAO = new BookingDB();
//	bookings = bookingDAO.findBookingsByApartmentNo(a.getApartmentNo());
//
//	int s1 = convertDateToInt(dateStart);
//	int e1 = convertDateToInt(dateEnd);
//	
//	for (int i = 0; i < bookings.size() && res ; i++) {
//		int s2 = convertDateToInt(bookings.get(i).getDateStart());
//		int e2 = s2+bookings.get(i).getNoOfNights();
//		
//		if(!(s1 <= e2 && e1 <= s2)) {
//			res = false;
//		}
//	}
//	return res;
//}