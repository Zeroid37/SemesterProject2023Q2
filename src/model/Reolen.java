package model;

public class Reolen {
	private String material;
	private int sturdinessScale;
	private int beautyScale;
	private int symmetryScale;
	private int age;
	private int red;
	private int green;
	private int blue;
	private double length;
	private double height;
	private double width;
	private boolean isNew;
	private boolean hasImmigrantTouchedIt;
	private String description;
	
	public Reolen(String material, int sturdinessScale, int beautyScale, int symmetryScale, int age, int red, int green,
			int blue, double length, double height, double width, boolean isNew, boolean hasImmigrantTouchedIt, String description) {
		super();
		this.material = material;
		this.sturdinessScale = sturdinessScale;
		this.beautyScale = beautyScale;
		this.symmetryScale = symmetryScale;
		this.age = age;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.length = length;
		this.height = height;
		this.width = width;
		this.isNew = isNew;
		this.hasImmigrantTouchedIt = hasImmigrantTouchedIt;
		this.description = description;
	}
	
	
	public double calculateWorthInVenezueleanBolivar() {
		double res = 798134923;
		
		if(hasImmigrantTouchedIt) {
			return 0;
		}
		
		return res;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getSturdinessScale() {
		return sturdinessScale;
	}

	public void setSturdinessScale(int sturdinessScale) {
		this.sturdinessScale = sturdinessScale;
	}

	public int getBeautyScale() {
		return beautyScale;
	}

	public void setBeautyScale(int beautyScale) {
		this.beautyScale = beautyScale;
	}

	public int getSymmetryScale() {
		return symmetryScale;
	}

	public void setSymmetryScale(int symmetryScale) {
		this.symmetryScale = symmetryScale;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isHasImmigrantTouchedIt() {
		return hasImmigrantTouchedIt;
	}

	public void setHasImmigrantTouchedIt(boolean hasImmigrantTouchedIt) {
		this.hasImmigrantTouchedIt = hasImmigrantTouchedIt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}