package spot;

public class SpotDto {
	private int spotCode;
	private String spotName;
	private int locationCode;
	private int spotCost;
	private boolean checkSpot;
	private boolean disabledSpot;
	
	public SpotDto(int spotCode,String spotName,int locationCode,int spotCost, boolean checkSpot,boolean disabledSpot) {
		this.spotCode = spotCode;
		this.spotName = spotName;
		this.locationCode = locationCode;
		this.spotCost = spotCost;
		this.checkSpot = checkSpot;
		this.disabledSpot = disabledSpot;
	}

	public int getSpotCode() {
		return spotCode;
	}

	public void setSpotCode(int spotCode) {
		this.spotCode = spotCode;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}

	public int getSpotCost() {
		return spotCost;
	}

	public void setSpotCost(int spotCost) {
		this.spotCost = spotCost;
	}

	public boolean isCheckSpot() {
		return checkSpot;
	}

	public void setCheckSpot(boolean checkSpot) {
		this.checkSpot = checkSpot;
	}

	public boolean isDisabledSpot() {
		return disabledSpot;
	}

	public void setDisabledSpot(boolean disabledSpot) {
		this.disabledSpot = disabledSpot;
	}
	
	
}
