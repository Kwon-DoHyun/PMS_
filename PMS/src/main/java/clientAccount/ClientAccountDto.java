package clientAccount;

import java.sql.Timestamp;

public class ClientAccountDto {
	private int accountCode;
	private String clientAccount;
	private int clientCredit;
	private String clientId;
	private Timestamp regDate;
	public ClientAccountDto(int accountCode, String clientAccount, int clientCredit, String clientId,
			Timestamp regDate) {
		super();
		this.accountCode = accountCode;
		this.clientAccount = clientAccount;
		this.clientCredit = clientCredit;
		this.clientId = clientId;
		this.regDate = regDate;
	}
	public int getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}
	public String getClientAccount() {
		return clientAccount;
	}
	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}
	public int getClientCredit() {
		return clientCredit;
	}
	public void setClientCredit(int clientCredit) {
		this.clientCredit = clientCredit;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
	
	
}
