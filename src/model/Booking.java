package model;

import java.time.LocalDate;

public class Booking {
	private String bookingNo;
	private String travelAgency;
	private LocalDate dateStart;
	private int noOfNights;
	private int discount;
	private boolean isDepositPaid;
	private int activityQuantityToday;
	private double price;
	private Apartment apartment;
	private Guest guest;
	
	
	
	public Booking(Apartment apartment, LocalDate dateStart, int noOfNights) {
		this.apartment = apartment;
		this.dateStart = dateStart;
		this.noOfNights = noOfNights;
		this.isDepositPaid = false;
		this.activityQuantityToday = 0;
	}
	
	
	
}
