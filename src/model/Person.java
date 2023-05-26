package model;

public abstract class Person {
	private String firstName;
	private String famName;
	private Address address;
	private String phone;
	private String email;
	private String userID;
	private String password;
	private boolean isAdmin;
	
	
	public Person(String firstName, String famName, Address address, String phone, String email) {
		this.firstName = firstName;
		this.famName = famName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.isAdmin = false;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getFamName() {
		return famName;
	}


	public void setFamName(String famName) {
		this.famName = famName;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
}
