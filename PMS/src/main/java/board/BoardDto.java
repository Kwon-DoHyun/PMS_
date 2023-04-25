package board;

import java.sql.Timestamp;

public class BoardDto {
	private int boardNumber;
	private String boardTitle;
	private String boardContents;
	private String userId;
	private Timestamp regDate;
	private int viewCount;
	
	public BoardDto(int boardNumber,String boardTitle, String boardContents,String userId,Timestamp regDate,int viewCount) {
		this.boardNumber = boardNumber;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.userId = userId;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}
	
	// 게시글 작성 (board_title , board_contents, user_id 순서)
	public BoardDto(String boardTitle, String boardContents,String userId) {
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.userId = userId;
	}
	// 게시글 업데이트(제목, 내용, 게시글 번호 )
	public BoardDto(int boardNumber,String boardTitle,String boardContents) {
		this.boardNumber = boardNumber;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
}
