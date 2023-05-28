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
	private String type;
	
	
	public Person(String firstName, String famName, Address address, String phone, String email, String type) {
		this.firstName = firstName;
		this.famName = famName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.isAdmin = false;
		this.type = type;
	}
	
	public Person(String firstName, String famName, Address address, String phone, String email, String userID, String password, String type, boolean isAdmin) {
		this.firstName = firstName;
		this.famName = famName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.userID = userID;
		this.password = password;
		this.isAdmin = isAdmin;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
