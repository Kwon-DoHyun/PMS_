package userAccount;

import java.sql.Timestamp;

public class UserAccount {
	private int accountCode;
	private String userAccount;
	private int userCredit;
	private String userId;
	Timestamp regDate;
	
	public UserAccount(int accountCode,String userAccount,int userCredit,String userId,Timestamp regDate) {
		this.accountCode = accountCode;
		this.userAccount = userAccount;
		this.userCredit  = userCredit;
		this.userId = userId;
		this.regDate = regDate;
	}
	
	public UserAccount(UserAccountDto userAccountDto ) {
		this.accountCode = userAccountDto.getAccountCode();
		this.userAccount = userAccountDto.getUserAccount();
		this.userCredit  = userAccountDto.getUserCredit();
		this.userId = userAccountDto.getUserId();
		this.regDate = userAccountDto.getRegDate();
	}

	public int getAccountCode() {
		return accountCode;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public int getUserCredit() {
		return userCredit;
	}

	public String getUserId() {
		return userId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}
	
	
}
