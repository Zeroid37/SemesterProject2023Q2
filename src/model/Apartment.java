package model;

public class Apartment {
	private String apartmentNo;
	private String apartmentType;
	private boolean hasBalcony;
	private String floorNo;
	private int numberOfKingBeds;
	private int numberOfQueenBeds;
	private int numberOfNormalBeds;
	private String viewDescription;
	private double pricePerNight;
	
	public Apartment(String apartmentNo, String apartmentType, boolean hasBalcony, String floorNo, int numberOfKingBeds,
					 int numberOfQueenBeds, int numberOfNormalBeds, String viewDescription, double pricePerNight) {
		this.apartmentNo = apartmentNo;
		this.apartmentType = apartmentType;
		this.hasBalcony = hasBalcony;
		this.floorNo = floorNo;
		this.numberOfKingBeds = numberOfKingBeds;
		this.numberOfQueenBeds = numberOfQueenBeds;
		this.numberOfNormalBeds = numberOfNormalBeds;
		this.viewDescription = viewDescription;
		this.setPricePerNight(pricePerNight);
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

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public int getNumberOfKingBeds() {
		return numberOfKingBeds;
	}

	public void setNumberOfKingBeds(int numberOfKingBeds) {
		this.numberOfKingBeds = numberOfKingBeds;
	}

	public int getNumberOfQueenBeds() {
		return numberOfQueenBeds;
	}

	public void setNumberOfQueenBeds(int numberOfQueenBeds) {
		this.numberOfQueenBeds = numberOfQueenBeds;
	}

	public int getNumberOfNormalBeds() {
		return numberOfNormalBeds;
	}

	public void setNumberOfNormalBeds(int numberOfNormalBeds) {
		this.numberOfNormalBeds = numberOfNormalBeds;
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
	
	
	
}
