package clientAccount.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class ClientAccountDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ClientAccountDao() {}
	private static ClientAccountDao instance = new ClientAccountDao();

	public static ClientAccountDao getInstance() {
		return instance;
	}
	
	
}
