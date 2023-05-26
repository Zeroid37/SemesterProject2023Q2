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
	
	public boolean isDepositPaid(double amount) {
		boolean res = false;
		
		if (amount>=(price/2)) {
			res = true;
		}
		return res;
	}
	
	public void calculateAndSetPrice() {
		this.price = apartment.getPricePerNight() * noOfNights;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getTravelAgency() {
		return travelAgency;
	}

	public void setTravelAgency(String travelAgency) {
		this.travelAgency = travelAgency;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public int getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isDepositPaid() {
		return isDepositPaid;
	}

	public void setDepositPaid(boolean isDepositPaid) {
		this.isDepositPaid = isDepositPaid;
	}

	public int getActivityQuantityToday() {
		return activityQuantityToday;
	}

	public void setActivityQuantityToday(int activityQuantityToday) {
		this.activityQuantityToday = activityQuantityToday;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}

