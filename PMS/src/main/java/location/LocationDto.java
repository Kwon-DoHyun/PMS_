package location;

public class LocationDto {
	
	private String locationCode;
	private int maxAreaCount;
	private int currentAreaCount;
	private String locationAddress;
	private String locationName;
	private String clientId;
	private boolean checkDisabledArea;
	
	public LocationDto(String locationCode,int maxAreaCount,int currentAreaCount,String locationAddress, String locationName,String clientId,boolean checkDisabledArea) {
		this.locationCode = locationCode;
		this.maxAreaCount = maxAreaCount;
		this.currentAreaCount = currentAreaCount;
		this.locationAddress = locationAddress;
		this.locationName = locationName;
		this.clientId = clientId;
		this.checkDisabledArea = checkDisabledArea;
	}



	public boolean isCheckDisabledArea() {
		return checkDisabledArea;
	}

	public void setCheckDisabledArea(boolean checkDisabledArea) {
		this.checkDisabledArea = checkDisabledArea;
	}



	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public int getMaxAreaCount() {
		return maxAreaCount;
	}

	public void setMaxAreaCount(int maxAreaCount) {
		this.maxAreaCount = maxAreaCount;
	}

	public int getCurrentAreaCount() {
		return currentAreaCount;
	}

	public void setCurrentAreaCount(int currentAreaCount) {
		this.currentAreaCount = currentAreaCount;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	

}
