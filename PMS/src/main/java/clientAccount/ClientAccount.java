package clientAccount;

import java.sql.Timestamp;

public class ClientAccount {
	private int accountCode;
	private String clientAccount;
	private int clientCredit;
	private String clientId;
	private Timestamp regDate;
	public ClientAccount(int accountCode, String clientAccount, int clientCredit, String clientId, Timestamp regDate) {
		
		this.accountCode = accountCode;
		this.clientAccount = clientAccount;
		this.clientCredit = clientCredit;
		this.clientId = clientId;
		this.regDate = regDate;
	}
	public int getAccountCode() {
		return accountCode;
	}
	public String getClientAccount() {
		return clientAccount;
	}
	public int getClientCredit() {
		return clientCredit;
	}
	public String getClientId() {
		return clientId;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	
	
	
}
