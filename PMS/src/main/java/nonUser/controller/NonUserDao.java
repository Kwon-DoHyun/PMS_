package nonUser.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import nonUser.NonUser;
import nonUser.NonUserDto;
import util.DBManager;

public class NonUserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public NonUserDao() {
		
	}
	private static NonUserDao instance = new NonUserDao();
	public static NonUserDao getInstance() {
		return instance;
	}
	
	public void nonUserBooking(NonUserDto nonUserDto) {
		NonUser nonUser = new NonUser(nonUserDto);
		this.conn = DBManager.getConnectionFromMySQL();
		
		if (this.conn != null) {
			String sql = "INSERT INTO non_user_booking (non_user_phone, non_user_car, non_user_password, location_code, spot_code, start_time, end_time,reg_date, total_cost, check_payment) VALUES (?, ?, ?, ?, ?, ?, null, ?,0, false)";
					
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, nonUser.getNonUserPhone());
				this.pstmt.setString(2, nonUser.getNonUserCar());
				this.pstmt.setString(3, nonUser.getNonUserPassword());
				this.pstmt.setInt(4, nonUser.getLocationCode());
				this.pstmt.setInt(5, nonUser.getSpotCode());
				this.pstmt.setTimestamp(6, nonUser.getStartTime());
				this.pstmt.setTimestamp(7, nonUser.getRegDate());

				this.pstmt.execute();

			} catch (SQLException e) {
				System.out.println("유저 생성 오류");
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}
	}
	
	public ArrayList<NonUser> getNonUserBookingInfoAll(){
		ArrayList<NonUser> list = new ArrayList<>();
		this.conn =DBManager.getConnectionFromMySQL();
		String sql = "select * from non_user_booking";
		try {
			
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(rs.next()) {
				int nonUserBookingCode = rs.getInt(1);
				String nonUserPhone = rs.getString(2);
				String nonUserCar = rs.getString(3);
				String nonUserPassword = rs.getString(4);
				int locationCode = rs.getInt(5);
				int spotCode = rs.getInt(6);
				Timestamp startTime = rs.getTimestamp(7);
				Timestamp endTime = rs.getTimestamp(8);
				Timestamp regDate = rs.getTimestamp(9);
				int totalCost = rs.getInt(10);
				boolean checkPayment = rs.getBoolean(11);
				
				NonUser nonUser = new NonUser(nonUserBookingCode,nonUserPhone,nonUserCar,nonUserPassword
						,locationCode,spotCode,startTime,endTime,regDate,totalCost,checkPayment);
				list.add(nonUser);
			}
			System.out.println("비회원 예약 조회 완료");
		} catch (Exception e) {
			System.out.println("비회원 예약 조회 실패");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public ArrayList<NonUser> getNonUserBookingListByKeyword(String keyword){
		ArrayList<NonUser> list = new ArrayList<>();
		String sql = "select * from non_user_booking " + keyword;
		System.out.println("sql : " + sql);
		
		this.conn =DBManager.getConnectionFromMySQL();
		if(this.conn != null) {
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				System.out.println("sql : " + this.pstmt);
				this.rs = this.pstmt.executeQuery();
				
				while(rs.next()) {
					int nonUserBookingCode = rs.getInt(1);
					String nonUserPhone = rs.getString(2);
					String nonUserCar = rs.getString(3);
					String nonUserPassword = rs.getString(4);
					int locationCode = rs.getInt(5);
					int spotCode = rs.getInt(6);
					Timestamp startTime = rs.getTimestamp(7);
					Timestamp endTime = rs.getTimestamp(8);
					Timestamp regDate = rs.getTimestamp(9);
					int totalCost = rs.getInt(10);
					boolean checkPayment = rs.getBoolean(11);
					
					NonUser nonUser = new NonUser(nonUserBookingCode,nonUserPhone,nonUserCar,nonUserPassword
							,locationCode,spotCode,startTime,endTime,regDate,totalCost,checkPayment);
					list.add(nonUser);
				}
				System.out.println("비회원 예약 키워드 조회 완료");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("비회원 예약 키워드 조회 실패");
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		return list;
	}
	

}
