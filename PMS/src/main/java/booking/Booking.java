package booking;

import java.sql.Timestamp;

public class Booking {
	private String userName;
	private String locationName;
	private String locationAddress;
	private String spotName;
	
	private int bookingCode;
	private String userId;
	private int locationCode;
	private int spotCode;
	private String carNumber;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp regDate;
	private int totalCost;
	private boolean checkPayment;
	

	public Booking(String userName,int locationCode,String locationName, String locationAddress,String spotName, String userId,int bookingCode, String carNumber,Timestamp startTime, Timestamp endTime, Timestamp regDate, int totalCost, boolean checkPayment,int spotCode) {
		this.userName = userName;
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.locationAddress = locationAddress;
		this.spotName = spotName;
		this.bookingCode = bookingCode;
		this.userId = userId;
		this.carNumber = carNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regDate = regDate;
		this.totalCost = totalCost;
		this.checkPayment = checkPayment;
		this.spotCode = spotCode;
	}
	
	public Booking(int bookingCode, String userId, int locationCode, int spotCode, String carNumber,Timestamp startTime, Timestamp endTime, Timestamp regDate, int totalCost, boolean checkPayment) {
		
		this.bookingCode = bookingCode;
		this.userId = userId;
		this.locationCode = locationCode;
		this.spotCode = spotCode;
		this.carNumber = carNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regDate = regDate;
		this.totalCost = totalCost;
		this.checkPayment = checkPayment;
	}
	
	public Booking(BookingDto bookingDto) {
		this.bookingCode = bookingDto.getBookingCode();
		this.userId = bookingDto.getUserId();
		this.locationCode = bookingDto.getLocationCode();
		this.spotCode = bookingDto.getSpotCode();
		this.carNumber = bookingDto.getCarNumber();
		this.startTime = bookingDto.getStartTime();
		this.endTime = bookingDto.getEndTime();
		this.regDate = bookingDto.getRegDate();
		this.totalCost = bookingDto.getTotalCost();
		this.checkPayment = bookingDto.isCheckPayment();
	}


	public String getLocationAddress() {
		return locationAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getSpotName() {
		return spotName;
	}

	public int getBookingCode() {
		return bookingCode;
	}


	public String getUserId() {
		return userId;
	}


	public int getLocationCode() {
		return locationCode;
	}


	public int getSpotCode() {
		return spotCode;
	}


	public String getCarNumber() {
		return carNumber;
	}


	public Timestamp getStartTime() {
		return startTime;
	}


	public Timestamp getEndTime() {
		return endTime;
	}


	public Timestamp getRegDate() {
		return regDate;
	}


	public int getTotalCost() {
		return totalCost;
	}


	public boolean isCheckPayment() {
		return checkPayment;
	}


	
	
}
