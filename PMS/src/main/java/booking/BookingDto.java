package booking;

import java.sql.Timestamp;

public class BookingDto {
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
	private int x;

	public BookingDto(int bookingCode, String userId, int locationCode, int spotCode, String carNumber,
			Timestamp startTime, Timestamp endTime, Timestamp regDate, int totalCost, boolean checkPayment) {

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

	public BookingDto(String userId, int locationCode, int spotCode, String carNumber, Timestamp startTime,
			Timestamp regDate, boolean checkPayment) {

		this.userId = userId;
		this.locationCode = locationCode;
		this.spotCode = spotCode;
		this.carNumber = carNumber;
		this.startTime = startTime;
		this.regDate = regDate;
		this.checkPayment = checkPayment;
	}

	public BookingDto(int bookingCode, int totalCost) {
		this.bookingCode = bookingCode;
		this.totalCost = totalCost;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(int bookingCode) {
		this.bookingCode = bookingCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}

	public int getSpotCode() {
		return spotCode;
	}

	public void setSpotCode(int spotCode) {
		this.spotCode = spotCode;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isCheckPayment() {
		return checkPayment;
	}

	public void setCheckPayment(boolean checkPayment) {
		this.checkPayment = checkPayment;
	}

}
