package model;

public class Apartment {
	private String apartmentNo;
	private String apartmentType;
	private boolean hasBalcony;
	private int floorNo;
	private int numberOfBeds;
	private String viewDescription;
	private double pricePerNight;
	
	public Apartment(String apartmentNo, String apartmentType, boolean hasBalcony, int floorNo,
					 int numberOfBeds, String viewDescription, double pricePerNight) {
		this.apartmentNo = apartmentNo;
		this.apartmentType = apartmentType;
		this.numberOfBeds = numberOfBeds;
		this.floorNo = floorNo;
		this.hasBalcony = hasBalcony;
		this.viewDescription = viewDescription;
		this.pricePerNight = pricePerNight;
	}

	public String getApartmentNo() {
		return apartmentNo;
	}

	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}

	public String getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
	}

	public boolean isHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public String getViewDescription() {
		return viewDescription;
	}

	public void setViewDescription(String viewDescription) {
		this.viewDescription = viewDescription;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
}
