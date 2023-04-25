package booking.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import booking.Booking;
import booking.BookingDto;

import util.DBManager;

public class BookingDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BookingDao() {
	};

	private static BookingDao instance = new BookingDao();

	public static BookingDao getInstance() {
		return instance;
	}

	// UPDATE booking SET end_time = NOW() WHERE booking_code = 1;
	// 출차시 null값이었다 end_time을 now() 를 이용해 출차시 시간으로 업데이트할수있다.
	public void newSpotBooking(BookingDto bookingDto) {
		Booking booking = new Booking(bookingDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "INSERT INTO booking (user_id, location_code, spot_code, car_number, start_time, end_time, reg_date, total_cost, check_payment) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, booking.getUserId());
				this.pstmt.setInt(2, booking.getLocationCode());
				this.pstmt.setInt(3, booking.getSpotCode());
				this.pstmt.setString(4, booking.getCarNumber());
				this.pstmt.setTimestamp(5, booking.getStartTime());
				this.pstmt.setTimestamp(6, null);
				this.pstmt.setTimestamp(7, booking.getRegDate());
				this.pstmt.setInt(8, 0);
				this.pstmt.setBoolean(9, false);
				System.out.println(this.pstmt);
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}
	}

	public ArrayList<Booking> getBookingInfoAll() {
		ArrayList<Booking> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "select * from booking_info_all";
 
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				String userName = rs.getString(1);
				int locationCode = rs.getInt(2);
				String locationName = rs.getString(3);
				String locationAddress = rs.getString(4);
				String spotName = rs.getString(5);
				String userId = rs.getString(6);
				int bookingCode = rs.getInt(7);
				String carNumber = rs.getString(8);
				Timestamp startTime = rs.getTimestamp(9);
				Timestamp endTime = rs.getTimestamp(10);
				Timestamp regDate = rs.getTimestamp(11);
				int totalCost = rs.getInt(12);
				boolean checkPayment = rs.getBoolean(13);
				int spotCode = rs.getInt(14);

				Booking booking = new Booking(userName, locationCode,locationName,locationAddress ,spotName, userId, bookingCode, carNumber,
						startTime, endTime, regDate, totalCost, checkPayment,spotCode);
				list.add(booking);


			}
			System.out.println("예약 조회 완료: "+sql);
			System.out.println("예약 조회 완료");
			return list;
		} catch (Exception e) {
			System.out.println("예약 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}

	//사업자 id와  location_code를 넣어 월 날짜와와 총액만 뽑아오는 리스트
	public ArrayList<String[]> getMonthAndTotal(String cientId, int location_code) {
		ArrayList<String[]> list = new ArrayList<String[]>();

		System.out.println("사업자 마이페이지 내 통계값 생성 구문 시작");
		this.conn = DBManager.getConnectionFromMySQL();
		
		
		if (this.conn != null) {
			String sql = "SELECT  DATE_FORMAT(end_time, '%Y-%m') AS month, SUM(total_cost) AS total, COUNT(DISTINCT DATE_FORMAT(end_time, '%y-%m-%d')) AS cnt  FROM booking INNER JOIN location ON booking.location_code = ? WHERE location.client_id = ? AND end_time IS NOT NULL GROUP BY month ORDER BY month ASC";


			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, location_code);
				this.pstmt.setString(2, cientId);

				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {
					System.out.println("배열 출력 확인용");
					String [] tmp = new String[3];
				    String month = rs.getString("month");
				    String total = String.valueOf(rs.getInt("total")) ;
				    String count = String.valueOf(rs.getInt("cnt")) ;
				    System.out.println(month+"월"+total+"원"+count+"건");
				    
				    tmp[0]=month;
				    tmp[1]=total;
				    tmp[2]=count;
				    list.add(tmp);
	
				}
			System.out.println("맵 생성이 완료됨");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	
	//사업자 id와  location_code를 넣어 일 날짜와 총액만 뽑아오는 리스트
	public ArrayList<String[]> getDayAndTotal(String cientId, int location_code) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		System.out.println("사업자 마이페이지 내 일단위성 구문 시작");
		this.conn = DBManager.getConnectionFromMySQL();
		
		
		if (this.conn != null) {
			String sql = "SELECT  DATE_FORMAT(end_time, '%y-%m-%d') AS day, SUM(total_cost) AS total ,COUNT(total_cost) AS cnt  FROM booking INNER JOIN location ON booking.location_code = ? WHERE location.client_id = ? AND end_time IS NOT NULL GROUP BY day ORDER BY day ASC";


			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, location_code);
				this.pstmt.setString(2, cientId);

				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {
					System.out.println("배열 출력 확인용");
					String [] tmp = new String[3];
				    String day = rs.getString("day");
				    String total = String.valueOf(rs.getInt("total")) ;
			
				    String count = String.valueOf(rs.getInt("cnt")) ;
				    System.out.println(day+"일"+total+"원"+count+"건");
				    tmp[0]=day;
				    tmp[1]=total;
				    tmp[2]=count;
				    list.add(tmp);
	
				}
			System.out.println("맵 생성이 완료됨");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}
	
	
	
	
	
	public ArrayList<Booking> getBookingInfoAllById(String id) {
		ArrayList<Booking> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM booking_info_all  WHERE user_id = ? ORDER BY (CASE WHEN check_payment = true THEN 0 ELSE 1 END) DESC, booking_code ASC";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, id);
			this.rs = this.pstmt.executeQuery();
			
			while (rs.next()) {
				String userName = rs.getString(1);
				int locationCode = rs.getInt(2);
				String locationName = rs.getString(3);
				String locationAddress = rs.getString(4);
				String spotName = rs.getString(5);
				String userId = rs.getString(6);
				int bookingCode = rs.getInt(7);
				String carNumber = rs.getString(8);
				Timestamp startTime = rs.getTimestamp(9);
				Timestamp endTime = rs.getTimestamp(10);
				Timestamp regDate = rs.getTimestamp(11);
				int totalCost = rs.getInt(12);
				boolean checkPayment = rs.getBoolean(13);
				int spotCode = rs.getInt(14);

				Booking booking = new Booking(userName, locationCode,locationName,locationAddress ,spotName, userId, bookingCode, carNumber,
						startTime, endTime, regDate, totalCost, checkPayment,spotCode);
				list.add(booking);

			}
			System.out.println("게시글 조회 완료");
			return list;
		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}
	
	
	
	public int getSpotCodeByBookingCode(int bookingCode) {
		int spotCode = 0;
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "select spot_code from booking_info_all WHERE booking_code = ? ";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, bookingCode);
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
				 spotCode = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return spotCode;

	}
	public String getBookingEndTime(int bookingCode) {
		String endTime = "";
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "select end_time from booking_info_all WHERE booking_code = ? ";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, bookingCode);
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
			endTime = rs.getString(1);

		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return endTime;

	}
	
	public int getLocationCode(int bookingCode) {
		int code = 0;
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "select location_code from booking WHERE booking_code = ? ";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, bookingCode);
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
			code = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return code;

	}
	
	public void endBooking(BookingDto bookingDto) {	//부킹 종료문
		System.out.println("부킹 업데이트 구문 진입");
		conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE booking SET end_time = ?, total_cost = ?,check_payment=true WHERE location_code =?";

			try {

				this.pstmt = this.conn.prepareStatement(sql);
				
			 
				this.pstmt.setTimestamp(1, bookingDto.getEndTime());
				this.pstmt.setInt(2, bookingDto.getTotalCost());
				this.pstmt.setInt(3, bookingDto.getBookingCode());
				System.out.println("업데이트 값:");
				System.out.println("종료 시간:"+bookingDto.getEndTime());
				System.out.println("예상 가격:"+bookingDto.getTotalCost());
				System.out.println("종료될 부킹:"+bookingDto.getBookingCode());
				
				this.pstmt.execute();
				System.out.println("부킹 종료 성공");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}

		}

	}

	public void deleteUserId(String userId ) {	//부킹 종료문
		System.out.println("부킹 내 탈퇴전 아이디 변경 구문 진입");
		conn = DBManager.getConnectionFromMySQL();
		
		if (this.conn != null) {
			//해당 방식은 delete라는 더미 유저에게 넘기는 임시방편임.
			String sql = "UPDATE booking SET user_id = 'deleted' WHERE user_id =?";

			try {

				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);
			 
			
				System.out.println("변경하려는 아이디 값:"+userId);
			
				
				this.pstmt.execute();
				System.out.println("업데이트 완료");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}

		}

	}
	
	
	
	public boolean deleteBookingForBookingCode(int booking_code) {
		System.out.println("부킹 DAO 내 고유코드 기반 삭제 메서드 진입");

		conn = DBManager.getConnectionFromMySQL();

		String sql = "DELETE FROM booking WHERE booking_code  = ?";

		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, booking_code);
			int result = this.pstmt.executeUpdate();

			if (result == 0) {
				System.out.println("삭제할 값이 없습니다");
				return false;
			} else {

				System.out.println("삭제 작업 완료");
				return true;
			}

		} catch (Exception e) {

			System.out.println("부킹 DAO 내 고유코드 기반 삭제 메서드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
	
	public void bookingUpdate(BookingDto bookingDto) {
		Booking booking = new Booking(bookingDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE booking SET total_cost = ? ,check_payment = ? WHERE booking_code = ?";
			try {

				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, booking.getTotalCost());
				this.pstmt.setBoolean(2, true);
				this.pstmt.setInt(3, booking.getBookingCode());

				this.pstmt.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}

		}
	}
	

	public void payment(int cost, int bookingCode) {
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE booking SET total_cost = ?, end_time = CURRENT_TIMESTAMP, check_payment = ? WHERE booking_code = ?";
			try {
				System.out.println("--------------");
				System.out.println(cost);
				
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cost);
				this.pstmt.setBoolean(2, true);
				this.pstmt.setInt(3, bookingCode);
				this.pstmt.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}

		}
	}
	
	
}
