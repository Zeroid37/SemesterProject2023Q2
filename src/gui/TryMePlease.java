package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.*;
import database.*;
import model.*;

public class TryMePlease {
	public static void main(String[] args) throws DataAccessException {
		BookingController b = new BookingController();
		
		List<Apartment> aList = new ArrayList<>();
		LocalDate start = LocalDate.of(2022, 12, 01);
		LocalDate end = LocalDate.of(2022, 12, 31);
		
		aList = b.searchForApartments(start, end, 5, 50000, "Single", 2, 2, true);
		
		for(Apartment a : aList) {
			//System.out.println(a.getApartmentNo());
		}
	}
}
