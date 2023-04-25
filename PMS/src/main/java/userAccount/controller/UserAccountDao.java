package userAccount.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;

public class UserAccountDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserAccountDao() {}
	
	private static UserAccountDao instance = new UserAccountDao();
	public static UserAccountDao getInstance() {
		return instance;
	}
	
	public int getAccountBalance(String userAccount) {
		int credit = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		String sql = "SELECT user_credit FROM user_accounts WHERE user_account = ? ";
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, userAccount);
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
			credit = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return credit;
	}
	public void creditUpdate(String userAccount,int credit) {
		
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE user_accounts SET user_credit = ? WHERE user_account = ?";
			try {
				System.out.println("--------------");
				System.out.println("userAccount:"+userAccount);
				System.out.println("credit:"+credit);
				
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, credit);
				this.pstmt.setString(2, userAccount);
				this.pstmt.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}
	
	public void paymentCost (int cost, String userId) {
		this.conn = DBManager.getConnectionFromMySQL();
		if(this.conn != null) {
			String sql = "UPDATE user_accounts set user_credit = ? WHERE user_id =?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cost);
				this.pstmt.setString(2, userId);
				this.pstmt.execute();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
			
		}
	}
	
}
