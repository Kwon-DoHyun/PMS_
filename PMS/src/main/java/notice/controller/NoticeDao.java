package notice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import board.Board;
import board.BoardDto;
import notice.Notice;
import notice.NoticeDto;
import util.DBManager;

public class NoticeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public NoticeDao() {
	}

	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	public void writerByNewNotice(NoticeDto noticeDto) {
		Notice notice = new Notice(noticeDto);
		int count = getNoticeListCount()+1;
		this.conn = DBManager.getConnectionFromMySQL();

		if(this.conn != null) {
			String sql = "INSERT INTO notice (notice_number,notice_title, notice_contents) VALUES(?,?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setInt(1, count);
				this.pstmt.setString(2, notice.getNoticeTitle());
				this.pstmt.setString(3, notice.getNoticeContents());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);

			}

		}
	}

	public int getNoticeListCount() {
		int count = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		String sql = "SELECT COUNT(*) FROM notice";

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

	public ArrayList<Notice> getNoticeAll() {

		ArrayList<Notice> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();
		String sql = "SELECT * FROM notice";


		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				Notice notice = null;

				int noticeNumber = rs.getInt(1);
				String noticeTitle = rs.getString(2);
				String noticeContents = rs.getString(3);
				Timestamp regDate = rs.getTimestamp(4);
				int viewCount = rs.getInt(5);

				notice = new Notice(noticeNumber, noticeTitle, noticeContents, regDate, viewCount);

				list.add(notice);

				// 확인용. 콘솔창에서 리스트 확인용(권기철)
				for (int i = 0; i < list.size(); i++) {
					System.out.println(notice.getNoticeNumber());
					System.out.println(notice.getNoticeTitle());
					System.out.println(notice.getNoticeContents());
					System.out.println(notice.getClientId());
					System.out.println(notice.getRegDate());
					System.out.println(notice.getViewCount());
				}

			}
			System.out.println("공지사항 완료");
			return list;
		} catch (Exception e) {
			System.out.println("공지사항 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;

	}

	// 글 번호로 해당 상세내용 확인하기
	public Notice getNoticeByNo(int number) {
		Notice notice = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM notice WHERE notice_number=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, number);
				this.rs = this.pstmt.executeQuery();
				while (this.rs.next()) {

					int boardNumber = this.rs.getInt(1);
					String boardTitle = this.rs.getString(2);
					String boardContents = this.rs.getString(3);
					Timestamp regDate = this.rs.getTimestamp(4);
					int viewCount = this.rs.getInt(5);

					notice = new Notice(boardNumber, boardTitle, boardContents, regDate, viewCount);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return notice;
	}

	public void updateViewCount(int noticeNumber) throws SQLException {

		this.conn = DBManager.getConnectionFromMySQL();

		try {

			String sql = "UPDATE notice SET view_count = view_count + 1 WHERE notice_number = ?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, noticeNumber);

			this.pstmt.executeUpdate();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}

	public int getNoticeCount() throws SQLException {
		this.conn = DBManager.getConnectionFromMySQL();
		int count = 0;
		if (this.conn != null) {
			String sql = "SELECT COUNT(*) FROM notice";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				if (rs.next()) {
					count = this.rs.getInt(1);
				}
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return count;
	}

	public ArrayList<Notice> getNoticeByRange(int start, int end) throws SQLException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "Select * from notice WHERE notice_number >= ? and notice_number <= ?;";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int noticeNumber = rs.getInt(1);
					String noticeTitle = rs.getString(2);
					String noticeContents = rs.getString(3);
					Timestamp regDate = rs.getTimestamp(4);
					int viewCount = rs.getInt(5);

					Notice notice = new Notice(noticeNumber, noticeTitle, noticeContents, regDate, viewCount);

					list.add(notice);
				}
			} finally {
				DBManager.close(conn, pstmt, rs);
			}

		}
		return list;
	}
	
		
		public void noticeUpdate(NoticeDto noticeDto) {
			Notice notice = new Notice(noticeDto);

			this.conn = DBManager.getConnectionFromMySQL();
			if (this.conn != null) {
				String sql = "UPDATE notice SET notice_title = ? , notice_contents = ? WHERE notice_number = ?";
				try {

					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, notice.getNoticeTitle());
					this.pstmt.setString(2, notice.getNoticeContents());
					// 게시글수정하는 페이지에서 얻는 boardNubmer를 이용해서 해당 글을 수정할 계획

					this.pstmt.setInt(3, notice.getNoticeNumber());

					this.pstmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}

			}
		}
		
		public void noticeDelete(NoticeDto noticeDto) {
			Notice notice = new Notice(noticeDto);
			this.conn = DBManager.getConnectionFromMySQL();

			String sql = "DELETE FROM notice WHERE board_number = ?";
			try {

				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, notice.getNoticeNumber());

				this.pstmt.execute();
				System.out.println("공지사항 삭제 성공");
			} catch (Exception e) {

				System.out.println("공지사항 삭제 실패");
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		
		public void noticeSort() {
			ArrayList<Notice> list = getNoticeAll();

			this.conn = DBManager.getConnectionFromMySQL();

			if (conn != null) {
				String Sql = "UPDATE notice SET notice_number=? where notice_number=?";
				try {
					int cnt = 1;
					for (int i = 0; i < list.size(); i++) {
						this.pstmt = conn.prepareStatement(Sql);
						this.pstmt.setInt(1, cnt++);
						this.pstmt.setInt(2, list.get(i).getNoticeNumber());
						this.pstmt.execute();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt);
				}
			}
		}
}
