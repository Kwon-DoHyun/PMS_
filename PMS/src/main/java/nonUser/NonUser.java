package nonUser;

import java.sql.Timestamp;

public class NonUser {
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
	
	public NonUser (int nonUserBookingCode,String nonUserPhone,String nonUserCar,String nonUserPassword,
			int locationCode,int spotCode,Timestamp startTime,Timestamp endTime,Timestamp regDate,int totalCost,boolean checkPayment) {
		
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
	
	public NonUser(NonUserDto nonUserDto) {
		this.nonUserBookingCode = nonUserDto.getNonUserBookingCode();
		this.nonUserPhone = nonUserDto.getNonUserPhone();
		this.nonUserCar =nonUserDto.getNonUserCar();
		this.nonUserPassword = nonUserDto.getNonUserPassword();
		this.locationCode = nonUserDto.getLocationCode();
		this.spotCode = nonUserDto.getSpotCode();
		this.startTime = nonUserDto.getStartTime();
		this.endTime = nonUserDto.getEndTime();
		this.regDate = nonUserDto.getRegDate();
		this.totalCost = nonUserDto.getTotalCost();
		this.checkPayment = nonUserDto.isCheckPayment();
	}

	public int getNonUserBookingCode() {
		return nonUserBookingCode;
	}

	public String getNonUserPhone() {
		return nonUserPhone;
	}

	public String getNonUserCar() {
		return nonUserCar;
	}

	public String getNonUserPassword() {
		return nonUserPassword;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public int getSpotCode() {
		return spotCode;
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