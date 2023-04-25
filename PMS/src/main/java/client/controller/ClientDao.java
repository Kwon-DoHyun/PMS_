package client.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import client.Client;
import client.ClientDto;
import user.User;
import user.UserDto;
import util.DBManager;

public class ClientDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ClientDao() {

	}

	private static ClientDao instance = new ClientDao();

	public static ClientDao getInstance() {
		return instance;
	}

	public void createClient(ClientDto clientDto) {
		Client client = new Client(clientDto);
		this.conn = DBManager.getConnectionFromMySQL();

		if (this.conn != null) {
			// id,비번,이름,전화,계좌 순
			String sql = "INSERT INTO clients (client_id, client_password, client_name, client_phone, client_account, client_number) VALUES (?,?,?,?,?,?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, client.getClientId());
				this.pstmt.setString(2, client.getClientPassword());
				this.pstmt.setString(3, client.getClientName());
				this.pstmt.setString(4, client.getClientPhone());
				this.pstmt.setString(5, client.getClientAccount());
				this.pstmt.setString(6, client.getClientNumber());
				System.out.println("생성할 사업자 데이터:" + client);

				int result = this.pstmt.executeUpdate();

				if (result == 0) {
					System.out.println("사업자 생성 실패");

				} else {
					System.out.println("사업자 생성 완료");

				}

			} catch (SQLException e) {
				System.out.println("사업자 생성 오류");
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}

	}

	public ArrayList<Client> getClientAll() {

		System.out.println("사업자 리스트 출력문 진입");
		ArrayList<Client> list = new ArrayList<>();

		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM clients";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				Client client = null;

				String clientId = rs.getString(1);
				String clientPassword = rs.getString(2);
				String clientName = rs.getString(3);
				String clientPhone = rs.getString(4);
				String clientAccount = rs.getString(5);
				String clientNumber = rs.getString(6);
				Timestamp regDate = rs.getTimestamp(7);

				client = new Client(clientId, clientPassword, clientName, clientPhone, clientAccount, clientNumber,
						regDate);

				list.add(client);

				// 확인용. 콘솔창에서 리스트 전체가 출력되는건 의도된 확인구문임(권도현)
				System.out.println(client);

			}
			System.out.println("사업자 리스트 출력문 완료");
			return list;
		} catch (Exception e) {
			System.out.println("서업자 리스트 출력 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}

	public Client findClientById(String clientId) {

		System.out.println("사업자 DAO 내 아이디 기반 사업자 검색문 진입");
		Client client = null;
		String sql = "SELECT * FROM clients WHERE client_id = ?";
		conn = DBManager.getConnectionFromMySQL();

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, clientId);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {

				String clientPassword = rs.getString(2);
				String clientName = rs.getString(3);
				String clientPhone = rs.getString(4);
				String clientAccount = rs.getString(5);
				String clientNumber = rs.getString(6);
				Timestamp regDate = rs.getTimestamp(7);

				client = new Client(clientId, clientPassword, clientName, clientPhone, clientAccount, clientNumber,
						regDate);

			}
			System.out.println("검색된 사업자:" + client);
			return client;

		} catch (Exception e) {

			System.out.println("사업자 DAO 내 아이디 기반 사업자 검색문 오류");
			e.printStackTrace();
		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return client;
	}

	public boolean duppleCheckedById(String clientId) {
		System.out.println("사업자 DAO 내 ID 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM clients WHERE client_id=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 아이디를 사용하는 사업자가 존재함");
				return false;

			} else {

				System.out.println("해당 아이디를 사용하는 사업자 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("사업자 DAO 내 ID 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public boolean duppleCheckedByClientPhone(String clientId) {
		System.out.println("사업자 DAO 내 전화번호 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM clients WHERE client_phone=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 전화번호를 사용하는 사업자가 존재함");
				return false;

			} else {

				System.out.println("해당 전화번호를 사용하는 사업자가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("사업자 DAO 내 전화번호 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public boolean duppleCheckedByClientAccount(String clientId) {
		System.out.println("사업자 DAO 내 계좌번호 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM clients WHERE client_account=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 계좌번호를 사용하는 사업자가 존재함");
				return false;

			} else {

				System.out.println("해당 계좌번호를 사용하는 사업자가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("사업자 DAO 내 계좌번호 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public boolean duppleCheckedByClientNumber(String clientNumber) {
		System.out.println("사업자 DAO 내 사업자번호 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM clients WHERE client_number=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientNumber);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 사업자번호를 사용하는 사업자가 존재함");
				return false;

			} else {

				System.out.println("해당 사업자번호를 사용하는 사업자가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("사업자 DAO 내 사업자번호 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public void UpdateClient(ClientDto client) {
		System.out.println("계정 업데이트 구문 진입");
		conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE clients SET client_password = ?, client_name = ?, client_phone = ?,client_account = ? ,client_number=? WHERE client_id =?";

			try {

				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setString(1, client.getClientPassword());
				this.pstmt.setString(2, client.getClientName());
				this.pstmt.setString(3, client.getClientPhone());
				this.pstmt.setString(4, client.getClientAccount());
				this.pstmt.setString(5, client.getClientNumber());

				System.out.println("업데이트 내 사업자번호:"+client.getClientNumber());
				this.pstmt.setString(6, client.getClientId());
				this.pstmt.execute();
				System.out.println("계정 업데이트 성공");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}

		}

	}

	public boolean deleteClientById(String clientId) {
		System.out.println("사업자 DAO 내 ID 기반 삭제 메서드 진입");

		conn = DBManager.getConnectionFromMySQL();

		String sql = "DELETE FROM clients WHERE client_id = ?";

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, clientId);
			int result = this.pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("삭제할 값이 없습니다");

			} else {

				System.out.println("삭제 작업 완료");
				return true;
			}

		} catch (Exception e) {

			System.out.println("사업자 DAO 내 ID 기반 삭제 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
	
	public String getClientByAccount(int locationCode) {
		String clientAccount = "";
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "select  client_account from clients WHERE location = ? ";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, locationCode);
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
			clientAccount = rs.getString(1);

		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return clientAccount;

	}

}
