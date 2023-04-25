package spot;

public class Spot {
	private int spotCode;
	private String spotName;
	private int locationCode;
	private int spotCost;
	private boolean checkSpot;
	private boolean disabledSpot;
	
	public Spot(int spotCode,String spotName,int locationCode,int spotCost, boolean checkSpot,boolean disabledSpot) {
		this.spotCode = spotCode;
		this.spotName = spotName;
		this.locationCode = locationCode;
		this.spotCost = spotCost;
		this.checkSpot = checkSpot;
		this.disabledSpot = disabledSpot;
	}
	public Spot(String spotName) {
		
		this.spotName = spotName;
	
	}

	public Spot(SpotDto spotDto) {
		this.spotCode = spotDto.getSpotCode();
		this.spotName = spotDto.getSpotName();
		this.locationCode = spotDto.getLocationCode();
		this.spotCost = spotDto.getSpotCost();
		this.checkSpot = spotDto.isCheckSpot();
		this.disabledSpot = spotDto.isDisabledSpot();
	}

	public int getSpotCode() {
		return spotCode;
	}

	public String getSpotName() {
		return spotName;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public int getSpotCost() {
		return spotCost;
	}

	public boolean isCheckSpot() {
		return checkSpot;
	}

	public boolean isDisabledSpot() {
		return disabledSpot;
	}
	
	
	
}
