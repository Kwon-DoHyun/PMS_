package board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import board.Board;
import board.BoardDto;
import util.DBManager;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardDao() {}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	// 새 게시글 등록 쿼리
	public void writeByNewBoard(BoardDto boardDto) {
		Board board = new Board(boardDto);
		int count = getBoardListCount()+1;
		this.conn = DBManager.getConnectionFromMySQL();
		System.out.println("등록될 글번호:"+count);

		if (this.conn != null) {
			String sql = "INSERT INTO board (board_Number,board_title, board_contents, user_id) VALUES(?,?,?,?)";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, count);
				this.pstmt.setString(2, board.getBoardTitle());
				this.pstmt.setString(3, board.getBoardContents());
				this.pstmt.setString(4, board.getUserId());
				System.out.println(this.pstmt);
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}
	}

	public int getBoardListCount() {
		int count = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		String sql = "SELECT COUNT(*) FROM board";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			   if (this.rs.next()) {
	                count = this.rs.getInt(1);
	                System.out.println("Number of records in board table: " + count);
	            }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return count;
	}

	// 전체 게시글 조회
	public ArrayList<Board> getBoardAll() {
		ArrayList<Board> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM board ORDER BY board_number DESC";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				int boardNumber = rs.getInt(1);
				String boardTitle = rs.getString(2);
				String boardContents = rs.getString(3);
				String userId = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				int viewCount = rs.getInt(6);

				Board board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);
				list.add(board);

				// 확인용. 콘솔창에서 리스트 확인용(권기철)
				for (Board boards : list) {
					System.out.println(boards.getBoardNumber());
					System.out.println(boards.getBoardTitle());
					System.out.println(boards.getBoardContents());
					System.out.println(boards.getUserId());
					System.out.println(boards.getRegDate());
					System.out.println(boards.getViewCount());

				}

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
	
	
	
	public ArrayList<Board> getBoardReverseAll() {
		ArrayList<Board> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "SELECT * FROM notice ORDER BY notice_number ASC";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				int boardNumber = rs.getInt(1);
				String boardTitle = rs.getString(2);
				String boardContents = rs.getString(3);
				String userId = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				int viewCount = rs.getInt(6);

				Board board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);
				list.add(board);

				// 확인용. 콘솔창에서 리스트 확인용(권기철)
				for (Board boards : list) {
					System.out.println(boards.getBoardNumber());
					System.out.println(boards.getBoardTitle());
					System.out.println(boards.getBoardContents());
					System.out.println(boards.getUserId());
					System.out.println(boards.getRegDate());
					System.out.println(boards.getViewCount());

				}

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
	
	
	

	// 키워드로 조회
	public ArrayList<Board> getBoardSearch(String search) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "Select * from board WHERE board_title like ? or user_id like ? or board_contents like ?";
			System.out.println("검색어: " + search);
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, '%' + search + '%');
				this.pstmt.setString(2, '%' + search + '%');
				this.pstmt.setString(3, '%' + search + '%');
				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {
					int boardNumber = this.rs.getInt(1);
					String boardTitle = this.rs.getString(2);
					String boardContents = this.rs.getString(3);
					String userId = this.rs.getString(4);
					Timestamp regDate = this.rs.getTimestamp(5);
					int viewCount = this.rs.getInt(6);

					Board board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);
					list.add(board);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	
	

	
	
	
	
	
	
	
	
	// 글 번호로 해당 상세내용 확인하기
	public Board getBoardByNo(int number) {
		Board board = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE board_number=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, number);
				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {

					int boardNumber = this.rs.getInt(1);
					String boardTitle = this.rs.getString(2);
					String boardContents = this.rs.getString(3);
					String userId = this.rs.getString(4);
					Timestamp regDate = this.rs.getTimestamp(5);
					int viewCount = this.rs.getInt(6);

					board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return board;
	}

	// 내 게시글 조회
	public ArrayList<Board> getMyBoard(String myId) {
		ArrayList<Board> list = new ArrayList<>();

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE user_id=?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, myId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int boardNumber = rs.getInt(1);
					String boardTitle = rs.getString(2);
					String boardContents = rs.getString(3);
					String userId = rs.getString(4);
					Timestamp regDate = rs.getTimestamp(5);
					int viewCount = rs.getInt(6);

					Board board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);
					list.add(board);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}

		return list;
	}

	public void boardUpdate(BoardDto boardDto) {
		Board board = new Board(boardDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE board SET board_title = ? , board_contents = ? WHERE board_number = ?";
			try {

				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, board.getBoardTitle());
				this.pstmt.setString(2, board.getBoardContents());
				// 게시글수정하는 페이지에서 얻는 boardNubmer를 이용해서 해당 글을 수정할 계획

				this.pstmt.setInt(3, board.getBoardNumber());

				this.pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}

		}
	}

	public void boardDelete(BoardDto boardDto) {
		Board board = new Board(boardDto);
		this.conn = DBManager.getConnectionFromMySQL();

		String sql = "DELETE FROM board WHERE board_number = ?";
		try {

			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, board.getBoardNumber());

			this.pstmt.execute();
			System.out.println("내 게시글 삭제 성공");
		} catch (Exception e) {

			System.out.println("내 게시글 삭제 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void boardSort() {
		ArrayList<Board> list = getBoardAll();

		this.conn = DBManager.getConnectionFromMySQL();

		if (conn != null) {
			String Sql = "UPDATE board SET board_number=? where board_number=?";
			try {
				int cnt = 1;
				for (int i = 0; i < list.size(); i++) {
					this.pstmt = conn.prepareStatement(Sql);
					this.pstmt.setInt(1, cnt++);
					this.pstmt.setInt(2, list.get(i).getBoardNumber());
					this.pstmt.execute();
				}
				System.out.println("update: " + this.pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public void updateViewCount(int boardNumber) throws SQLException {

		this.conn = DBManager.getConnectionFromMySQL();

		try {

			String sql = "UPDATE board SET view_count = view_count + 1 WHERE board_number = ?";
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, boardNumber);

			this.pstmt.executeUpdate();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	public ArrayList<Board> getBoardByRange(int start, int end) throws SQLException {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "Select * from board WHERE board_number >= ? and board_number <= ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, start);
				this.pstmt.setInt(2, end);
				this.rs = this.pstmt.executeQuery(); 
				while (this.rs.next()) {
					int boardNumber = rs.getInt(1);
					String boardTitle = rs.getString(2);
					String boardContents = rs.getString(3);
					String userId = rs.getString(4);
					Timestamp regDate = rs.getTimestamp(5);
					int viewCount = rs.getInt(6);

					Board board = new Board(boardNumber, boardTitle, boardContents, userId, regDate, viewCount);

					list.add(board);
				}
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	
	
	
	
}
