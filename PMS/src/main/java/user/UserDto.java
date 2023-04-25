package user;

import java.sql.Timestamp;

public class UserDto {
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userAccount;
	private Timestamp regDate;
	
	public UserDto(String userId, String userPassword,String userName,String userPhone,String userAccount,Timestamp regDate) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAccount = userAccount;
		this.regDate = regDate;
	}

	public UserDto(String userId, String userPassword,String userName,String userPhone,String userAccount) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAccount = userAccount;

	}
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
