package model;

public class Guest extends Person{
	private int guestNo;
	private String country;
	
	public Guest(String firstName, String famName, Address address, String phone, String email, int guestNo, String country) {
		super(firstName, famName, address, phone, email);
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
