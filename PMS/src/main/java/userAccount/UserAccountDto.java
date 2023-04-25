package userAccount;

import java.sql.Timestamp;

public class UserAccountDto {
	private int accountCode;
	private String userAccount;
	private int userCredit;
	private String userId;
	Timestamp regDate;
	
	public UserAccountDto(int accountCode,String userAccount,int userCredit,String userId,Timestamp regDate) {
		this.accountCode = accountCode;
		this.userAccount = userAccount;
		this.userCredit  = userCredit;
		this.userId = userId;
		this.regDate = regDate;
	}

	public int getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getUserCredit() {
		return userCredit;
	}

	public void setUserCredit(int userCredit) {
		this.userCredit = userCredit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
