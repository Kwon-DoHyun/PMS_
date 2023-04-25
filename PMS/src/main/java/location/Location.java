package location;

public class Location {
	private String locationCode;
	private String locationName;
	private String locationAddress;
	private int currentAreaCount;
	private int maxAreaCount;
	private boolean checkDisabledArea;
	private String clientId;
	
	public Location(String locationCode,int maxAreaCount,int currentAreaCount,String locationAddress, String locationName,String clientId,boolean checkDisabledArea) {
		this.locationCode = locationCode;
		this.maxAreaCount = maxAreaCount;
		this.currentAreaCount = currentAreaCount;
		this.locationAddress = locationAddress;
		this.locationName = locationName;
		this.clientId = clientId;
		this.checkDisabledArea = checkDisabledArea;
	}
	
	public Location(String locationCode,String locationName) {
		this.locationCode = locationCode;
		this.locationName = locationName;
	}
	
	public Location(LocationDto locationDto) {
		this.locationCode = locationDto.getLocationCode();
		this.maxAreaCount = locationDto.getMaxAreaCount();
		this.currentAreaCount = locationDto.getCurrentAreaCount();
		this.locationAddress = locationDto.getLocationAddress();
		this.locationName = locationDto.getLocationName();
		this.clientId = locationDto.getClientId();
		this.checkDisabledArea = locationDto.isCheckDisabledArea();
	}
	
	public boolean isCheckDisabledArea() {
		return checkDisabledArea;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public int getMaxAreaCount() {
		return maxAreaCount;
	}

	public int getCurrentAreaCount() {
		return currentAreaCount;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getClientId() {
		return clientId;
	}
	

}
