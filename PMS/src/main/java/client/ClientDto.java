package client;

import java.sql.Timestamp;

public class ClientDto {
	private String clientId;
	private String clientPassword;
	private String clientName;
	private String clientPhone;
	private String clientAccount;
	private String clientNumber;
	private Timestamp regDate;
	
	//가입 일자 포함
	public ClientDto(String clientId,String clientPassword,String clientName,String clientPhone,String clientAccount,String clientNumber,Timestamp regDate) {
		this.clientId = clientId;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.clientAccount = clientAccount;
		this.clientNumber = clientNumber;
		this.regDate = regDate;
	}
	// 가일 일자제외
	public ClientDto(String clientId,String clientPassword,String clientName,String clientPhone,String clientAccount,String clientNumber) {
		this.clientId = clientId;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.clientAccount = clientAccount;
		this.clientNumber = clientNumber;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public String getClientAccount() {
		return clientAccount;
	}
	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
	
}
