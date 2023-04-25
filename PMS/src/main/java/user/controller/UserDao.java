package user.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import user.User;
import user.UserDto;
import util.DBManager;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// 유저 생성
	public void createUser(UserDto userDto) {
		User user = new User(userDto);
		this.conn = DBManager.getConnectionFromMySQL();

		if (this.conn != null) {
			// id,비번,이름,전화,계좌 순
			String sql = "INSERT INTO users (user_id, user_password, user_name, user_phone, user_account) VALUES (?,?,?,?,?)";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, user.getUserId());
				this.pstmt.setString(2, user.getUserPassword());
				this.pstmt.setString(3, user.getUserName());
				this.pstmt.setString(4, user.getUserPhone());
				this.pstmt.setString(5, user.getUserAccount());
				System.out.println("생성할 유저 데이터:" + user);

				int result = this.pstmt.executeUpdate();

				if (result == 0) {
					System.out.println("유저 생성 실패");

				} else {
					System.out.println("유저 생성 완료");

				}

			} catch (SQLException e) {
				System.out.println("유저 생성 오류");
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}

	}

	public ArrayList<User> getUserAll() {

		System.out.println("유저 리스트 출력문 진입");
		ArrayList<User> list = new ArrayList<>();

		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM users";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				User user = null;

				String userId = rs.getString(1);
				String userPassword = rs.getString(2);
				String userName = rs.getString(3);
				String userPhone = rs.getString(4);
				String userAccount = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);

				user = new User(userId, userPassword, userName, userPhone, userAccount, regDate);

				list.add(user);

				// 확인용. 콘솔창에서 리스트 전체가 출력되는건 의도된 확인구문임(권도현)
				System.out.println(user);

			}
			System.out.println("유저 리스트 출력문 완료");
			return list;
		} catch (Exception e) {
			System.out.println("유저 리스트 출력 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}
	//ID 기반으로 유저 이름 찾기
	public String getUserName(String userId) {

		System.out.println("유저 DAO 내 아이디 기반 유저 이름 검색문 진입");
		String name = null;
		String sql = "SELECT user_name FROM users WHERE user_id = ?";
		conn = DBManager.getConnectionFromMySQL();

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, userId);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {

				name = rs.getString(1);

			}
			System.out.println("검색된 이름:" + name);
			return name;

		} catch (Exception e) {

			System.out.println("유저 DAO 내 아이디 기반 유저 이름 검색문 오류");
			e.printStackTrace();
		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return name;
	}

	public User findUserById(String userId) {

		System.out.println("유저 DAO 내 아이디 기반 유저 검색문 진입");
		User user = null;
		String sql = "SELECT * FROM users WHERE user_id = ?";
		conn = DBManager.getConnectionFromMySQL();

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, userId);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {

				String userPassword = rs.getString(2);
				String userName = rs.getString(3);
				String userPhone = rs.getString(4);
				String userAccount = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);

				user = new User(userId, userPassword, userName, userPhone, userAccount, regDate);

			}
			System.out.println("검색된 유저:" + user);
			return user;

		} catch (Exception e) {

			System.out.println("유저 DAO 내 아이디 기반 유저 검색문 오류");
			e.printStackTrace();
		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return user;
	}

	public User findUserByPhoneNumber(String userPhone) {

		User user = null;
		String sql = "SELECT * FROM users WHERE user_phone = ?";
		conn = DBManager.getConnectionFromMySQL();

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, userPhone);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {

				String user_id = rs.getString(1);
				String userPassword = rs.getString(2);
				String userName = rs.getString(3);
				String userAccount = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);

				user = new User(user_id, userPassword, userName, userPhone, userAccount, regDate);

			}
			System.out.println("검색된 유저:" + user);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBManager.close(conn, pstmt, rs);

		}

		return user;
	}

	

	public boolean isPaid(String userId) {
		System.out.println("유저 DAO 내 결제여부 체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT COUNT(*) as count FROM booking WHERE user_id=? AND check_payment='false'";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1)==0) {
					System.out.println("미결제 내역이 존재하지 않음");
			
					return true;
					
					
				} else {
					
					System.out.println("검색결과:"+rs.getInt(1));
					System.out.println("미결제 내역이 존재함");
					
					return false;
				}
			}else {
				

				System.out.println("sql 오류가 존재함");
				
				return false;
			}
			


		} catch (Exception e) {
			System.out.println("유저 DAO 내 ID 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}
	
	
	
	//===========================================
	public boolean duppleCheckedById(String userId) {
		System.out.println("유저 DAO 내 ID 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM users WHERE user_id=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 아이디를 사용하는 유저가 존재함");
				return false;

			} else {

				System.out.println("해당 아이디를 사용하는 유저가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("유저 DAO 내 ID 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public boolean duppleCheckedByUserPhone(String userPhone) {
		System.out.println("유저 DAO 내 전화번호 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM users WHERE user_phone=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPhone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 전화번호를 사용하는 유저가 존재함");
				return false;

			} else {

				System.out.println("해당 전화번호를 사용하는 유저가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("유저 DAO 내 전화번호 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public boolean duppleCheckedByUserAccount(String userAccount) {
		System.out.println("유저 DAO 내 계좌번호 중복체크 메서드 진입");
		conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM users WHERE user_account=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userAccount);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("해당 계좌번호를 사용하는 유저가 존재함");
				return false;

			} else {

				System.out.println("해당 계좌번호를 사용하는 유저가 존재하지 않음");
				return true;

			}

		} catch (Exception e) {
			System.out.println("유저 DAO 내 계좌번호 중복체크 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		System.out.println("try 문 오류가 있었음");

		return false;
	}

	public void UpdateUser(UserDto userDto) {
		System.out.println("계정 업데이트 구문 진입");
		conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE users SET user_password = ?, user_name = ?, user_phone = ?,user_account = ? WHERE user_id =?";

			try {

				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setString(1, userDto.getUserPassword());
				this.pstmt.setString(2, userDto.getUserName());
				this.pstmt.setString(3, userDto.getUserPhone());
				this.pstmt.setString(4, userDto.getUserAccount());
				this.pstmt.setString(5, userDto.getUserId());
				this.pstmt.execute();
				System.out.println("계정 업데이트 성공");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}

		}

	}

	public boolean deleteUserById(String userId) {
		System.out.println("유저 DAO 내 ID 기반 삭제 메서드 진입");

		conn = DBManager.getConnectionFromMySQL();

		String sql = "DELETE FROM users WHERE user_id = ?";

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, userId);
			int result = this.pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("삭제할 값이 없습니다");

			} else {

				System.out.println("삭제 작업 완료");
				return true;
			}

		} catch (Exception e) {

			System.out.println("유저 DAO 내 ID 기반 삭제 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}

}
