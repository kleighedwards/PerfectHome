package data;

public class ZillowDTO {
	
	private int zillowId;
	private String street;
	private int zip;
	private String city;
	private String state;
	
	private String useCode;
	private int bedrooms;
	private double bathrooms;
	private int finishedSqFoot;
	private int totalSqFoot;
	private int yearBuilt;
	private int floors;
	private String finishedBasement;
	
	private String imageUrl;

	public int getZillowId() {
		return zillowId;
	}

	public void setZillowId(int zillowId) {
		this.zillowId = zillowId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUseCode() {
		return useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public double getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(double bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getFinishedSqFoot() {
		return finishedSqFoot;
	}

	public void setFinishedSqFoot(int finishedSqFoot) {
		this.finishedSqFoot = finishedSqFoot;
	}

	public int getTotalSqFoot() {
		return totalSqFoot;
	}

	public void setTotalSqFoot(int totalSqFoot) {
		this.totalSqFoot = totalSqFoot;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public String getFinishedBasement() {
		return finishedBasement;
	}

	public void setFinishedBasement(String finishedBasement) {
		this.finishedBasement = finishedBasement;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "ZillowDTO [zillowId=" + zillowId + ", street=" + street + ", zip=" + zip + ", city=" + city + ", state="
				+ state + ", useCode=" + useCode + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms
				+ ", finishedSqFoot=" + finishedSqFoot + ", totalSqFoot=" + totalSqFoot + ", yearBuilt=" + yearBuilt
				+ ", floors=" + floors + ", finishedBasement=" + finishedBasement + ", imageUrl=" + imageUrl + "]";
	}
	
}
