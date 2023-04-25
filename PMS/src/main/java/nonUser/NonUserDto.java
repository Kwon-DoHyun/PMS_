package nonUser;

import java.sql.Timestamp;

public class NonUserDto {
	private int nonUserBookingCode;
	private String nonUserPhone;
	private String nonUserCar;
	private String nonUserPassword;
	private int locationCode;
	private int spotCode;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp regDate;
	private int totalCost;
	private boolean checkPayment;

	public NonUserDto(int nonUserBookingCode, String nonUserPhone, String nonUserCar, String nonUserPassword,
			int locationCode, int spotCode, Timestamp startTime, Timestamp endTime, Timestamp regDate, int totalCost,
			boolean checkPayment) {

		this.nonUserBookingCode = nonUserBookingCode;
		this.nonUserPhone = nonUserPhone;
		this.nonUserCar =nonUserCar;
		this.nonUserPassword = nonUserPassword;
		this.locationCode = locationCode;
		this.spotCode = spotCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regDate = regDate;
		this.totalCost = totalCost;
		this.checkPayment = checkPayment;
	}

	public NonUserDto(String nonUserPhone, String nonUserCar, String nonUserPassword, int locationCode, int spotCode,
			Timestamp startTime, Timestamp regDate) {

		this.nonUserPhone = nonUserPhone;
		this.nonUserCar =nonUserCar;
		this.nonUserPassword = nonUserPassword;
		this.locationCode = locationCode;
		this.spotCode = spotCode;
		this.startTime = startTime;
		this.regDate = regDate;
	}

	public int getNonUserBookingCode() {
		return nonUserBookingCode;
	}

	public void setNonUserBookingCode(int nonUserBookingCode) {
		this.nonUserBookingCode = nonUserBookingCode;
	}

	public String getNonUserPhone() {
		return nonUserPhone;
	}

	public void setNonUserPhone(String nonUserPhone) {
		this.nonUserPhone = nonUserPhone;
	}

	public String getNonUserCar() {
		return nonUserCar;
	}

	public void setNonUserCar(String nonUserCar) {
		this.nonUserCar = nonUserCar;
	}

	public String getNonUserPassword() {
		return nonUserPassword;
	}

	public void setNonUserPassword(String nonUserPassword) {
		this.nonUserPassword = nonUserPassword;
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
