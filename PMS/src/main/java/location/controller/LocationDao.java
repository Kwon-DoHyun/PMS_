package location.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;
import location.Location;
import util.DBManager;

public class LocationDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public LocationDao() {
	}

//인스턴스
	private static LocationDao instance = new LocationDao();

	public static LocationDao getInstance() {
		return instance;
	}

//키워드로 찾기
	public ArrayList<Location> getLocationListByKeyword(String keyWord) {
		ArrayList<Location> list = new ArrayList<Location>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {

			String sql = "SELECT * FROM location WHERE location_name like ? or location_address like ? ORDER BY location_name ";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, '%' + keyWord + '%');
				this.pstmt.setString(2, '%' + keyWord + '%');
				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {
					String locationCode = this.rs.getString(1);
					int maxAreaCount = this.rs.getInt(2);
					int currentAreaCount = this.rs.getInt(3);
					String locationAddress = this.rs.getString(4);
					String locationName = this.rs.getString(5);
					String clientId = this.rs.getString(6);
					boolean checkDisabledArea = this.rs.getBoolean(7);

					Location location = new Location(locationCode, maxAreaCount, currentAreaCount, locationAddress,
							locationName, clientId, checkDisabledArea);
					list.add(location);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	// 로케이션 이름으로 찾기
	public Location getLocationByName(String name) {
		System.out.println("dao안에 이름 : " + name);
		Location location = null;

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM location WHERE location_name=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String locationCode = this.rs.getString(1);
					int maxAreaCount = this.rs.getInt(2);
					int currentAreaCount = this.rs.getInt(3);
					String locationAddress = this.rs.getString(4);
					String locationName = this.rs.getString(5);
					String clientId = this.rs.getString(6);
					boolean checkDisabledArea = this.rs.getBoolean(7);
					location = new Location(locationCode, maxAreaCount, currentAreaCount, locationAddress, locationName,
							clientId, checkDisabledArea);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return location;
	}
	//코드로 로케이션 찾기
	public Location getLocationByCode(String code) {
		System.out.println("dao안에 코드 : " + code);
		Location location = null;

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM location WHERE location_code=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, code);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String locationCode = this.rs.getString(1);
					int maxAreaCount = this.rs.getInt(2);
					int currentAreaCount = this.rs.getInt(3);
					String locationAddress = this.rs.getString(4);
					String locationName = this.rs.getString(5);
					String clientId = this.rs.getString(6);
					boolean checkDisabledArea = this.rs.getBoolean(7);
					location = new Location(locationCode, maxAreaCount, currentAreaCount, locationAddress, locationName,
							clientId, checkDisabledArea);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return location;
	}

	public ArrayList<Location> getLocationByClientId(String clientId) {

		ArrayList<Location> list = new ArrayList<>();

		System.out.println("dao안에 이름 : " + clientId);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM location WHERE client_id=?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, clientId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Location location = null;
					String locationCode = this.rs.getString(1);
					int maxAreaCount = this.rs.getInt(2);
					int currentAreaCount = this.rs.getInt(3);
					String locationAddress = this.rs.getString(4);
					String locationName = this.rs.getString(5);

					boolean checkDisabledArea = this.rs.getBoolean(7);
					location = new Location(locationCode, maxAreaCount, currentAreaCount, locationAddress, locationName,
							clientId, checkDisabledArea);
					System.out.println("장소명:" + locationName);
					list.add(location);

				}
				System.out.println("리스트 생성 완료");
				return list;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

}
