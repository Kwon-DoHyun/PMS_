package user;

import java.sql.Timestamp;

public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhone;
	private String userAccount;
	private Timestamp regDate;
	
	//가입날짜 포함
	public User(String userId, String userPassword,String userName,String userPhone,String userAccount,Timestamp regDate) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAccount = userAccount;
		this.regDate = regDate;
	}
	//가입날짜 제외
	public User(String userId, String userPassword,String userName,String userPhone,String userAccount) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAccount = userAccount;

	}
	
	
	public User(UserDto userDto) {
		this.userId = userDto.getUserId();
		this.userPassword = userDto.getUserPassword();
		this.userName = userDto.getUserName();
		this.userPhone = userDto.getUserPhone();
		this.userAccount = userDto.getUserAccount();
		this.regDate = userDto.getRegDate();
	}
	
	

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public Timestamp getRegDate() {
		return regDate;
	}
	

	public String toString() {
	
return String.format("아이디:%s 비밀번호:%s 이름:%s 전화번호:%s 계좌:%s ",userId, userPassword,userName,userPhone,userAccount);
	}
}
