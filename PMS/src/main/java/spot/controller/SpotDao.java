package spot.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import spot.Spot;
import util.DBManager;

public class SpotDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public SpotDao() {}
	
	private static SpotDao instance = new SpotDao();

	public static SpotDao getInstance() {
		return instance;
	}
	// 주차장 층 목록
	public ArrayList<Spot> getSelectSpot(int locationNumber) {
		ArrayList<Spot> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();
		System.out.println("locationName: "+locationNumber);
		String sql = "SELECT LEFT(spot_name, 2) AS floorName, COUNT(*) AS count\r\n"
				+ "FROM parking_spot\r\n"
				+ "WHERE location_code IN (\r\n"
				+ "    SELECT location_code\r\n"
				+ "    FROM location\r\n"
				+ "    WHERE location_code = ?\r\n"
				+ ")\r\n"
				+ "AND check_spot = ?\r\n"
				+ "GROUP BY LEFT(spot_name, 2)";
		
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, locationNumber);
			this.pstmt.setBoolean(2, true);
			this.rs = this.pstmt.executeQuery();
	
			while (rs.next()) {
				
				String spotName = rs.getString(1);

				Spot spot = new Spot(spotName);
				list.add(spot);
			}
			
			System.out.println("구역 조회완료");
			return list;
		} catch (Exception e) {
			System.out.println("구역 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
	// 주차장 코드와 / 이름 / 고객이 선택한 입차일시를 받아와서 해당 일시에 예약자없는 구역만 조회
	public ArrayList<Spot> getSelectSpotName(int locationNumber,String spotNames ,String startTime) {
		ArrayList<Spot> list = new ArrayList<>();
//		Booking booking = new Booking(bookingDto);
		this.conn = DBManager.getConnectionFromMySQL();
		System.out.println("locationNumber%%^^^^^^: "+locationNumber);
		System.out.println("spotNames%%^^^^^^: "+spotNames);
		System.out.println("startTime%%^^^^^^: "+startTime);
		String sql = "SELECT p.* FROM parking_spot p WHERE p.location_code = ? AND p.check_spot = true AND LEFT(p.spot_name, 2) = ? AND p.spot_code NOT IN (SELECT b.spot_code FROM booking b WHERE b.check_payment = false AND b.location_code = ? AND b.start_time <= ?)AND p.spot_code NOT IN (SELECT nub.spot_code FROM non_user_booking nub WHERE nub.check_payment = false AND nub.location_code = ? AND nub.start_time <= ?)";
		
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, locationNumber);
			this.pstmt.setString(2, spotNames);
			this.pstmt.setInt(3, locationNumber);
			this.pstmt.setString(4, startTime);
			this.pstmt.setInt(5, locationNumber);
			this.pstmt.setString(6, startTime);
			
			this.rs = this.pstmt.executeQuery();
	
			while (rs.next()) {
				
				int spotCode = rs.getInt(1);
				String spotName = rs.getString(2);
				int locationCode = rs.getInt(3);
				int spotCost = rs.getInt(4);
				boolean checkSpot = rs.getBoolean(5);
				boolean disabledSpot = rs.getBoolean(6);
				
				Spot spot = new Spot(spotCode, spotName,locationCode,spotCost,checkSpot,disabledSpot);
				list.add(spot);

			}
			
			System.out.println("list: "+ list.size());
			System.out.println("구역 조회완료");
			return list;
		} catch (Exception e) {
			System.out.println("구역 조회 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}
	
	public Spot getSpotBySpotCode(int spotCode) {
		this.conn = DBManager.getConnectionFromMySQL();
		Spot spot = null;
		if(this.conn!=null) {
			String sql = "select * from parking_spot where spot_code = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, spotCode);
				this.rs = this.pstmt.executeQuery();
				
				this.rs.next();
				String spotName = this.rs.getString(2);
				int locationCode = this.rs.getInt(3);
				int spotCost = this.rs.getInt(4);
				boolean checkSpot = this.rs.getBoolean(5);
				boolean disalbedSpot = this.rs.getBoolean(6);
				
				spot = new Spot(spotCode, spotName,locationCode, spotCost, checkSpot,disalbedSpot);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
		}
		return spot;
	}
}
