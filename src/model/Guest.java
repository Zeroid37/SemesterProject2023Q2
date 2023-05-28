package model;

import java.util.Random;

public class Guest extends Person{
	private int guestNo;
	private String country;
	
	public Guest(String firstName, String famName, Address address, String phone, String email, String country) {
		super(firstName, famName, address, phone, email);
		Random rand = new Random();
		this.guestNo = rand.nextInt(999999999);
		this.country = country;
	}
	
	public Guest(String firstName, String famName, Address address, String phone, String email, String userID, String password, boolean isAdmin, String country, int guestNo) {
		super(firstName, famName, address, phone, email, userID, password, isAdmin);
		this.guestNo = guestNo;
		this.country = country;
	}

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
