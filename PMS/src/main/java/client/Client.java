package client;

import java.sql.Timestamp;

public class Client {
	private String clientId;
	private String clientPassword;
	private String clientName;
	private String clientPhone;
	private String clientAccount;
	private String clientNumber;
	private Timestamp regDate;

	// 가입 일자 포함
	public Client(String clientId, String clientPassword, String clientName, String clientPhone, String clientAccount,
			String clientNumber, Timestamp regDate) {
		this.clientId = clientId;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.clientAccount = clientAccount;
		this.clientNumber = clientNumber;
		this.regDate = regDate;
	}

	// 가일 일자제외
	public Client(String clientId, String clientPassword, String clientName, String clientPhone, String clientAccount,
			String clientNumber) {
		this.clientId = clientId;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.clientAccount = clientAccount;
		this.clientNumber = clientNumber;

	}

	public Client(ClientDto clientDto) {
		this.clientId = clientDto.getClientId();
		this.clientPassword = clientDto.getClientPassword();
		this.clientName = clientDto.getClientName();
		this.clientPhone = clientDto.getClientPhone();
		this.clientAccount = clientDto.getClientAccount();
		this.clientNumber = clientDto.getClientNumber();
		this.regDate = clientDto.getRegDate();

	}

	public String getClientId() {
		return clientId;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public String toString() {

		return String.format("아이디:%s 비밀번호:%s 이름:%s 전화번호:%s 계좌:%s 사업자번호:%s", clientId, clientPassword, clientName,
				clientPhone, clientAccount, clientNumber);
	}

}
