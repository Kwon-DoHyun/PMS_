package board;

import java.sql.Timestamp;

public class Board {
	private int boardNumber;
	private String boardTitle;
	private String boardContents;
	private String userId;
	private Timestamp regDate;
	private int viewCount;
	
	public Board(int boardNumber,String boardTitle, String boardContents,String userId,Timestamp regDate,int viewCount) {
		this.boardNumber = boardNumber;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.userId = userId;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}
	
	public Board(BoardDto boardDto) {
		this.boardNumber = boardDto.getBoardNumber();
		this.boardTitle = boardDto.getBoardTitle();
		this.boardContents = boardDto.getBoardContents();
		this.userId = boardDto.getUserId();
		this.regDate = boardDto.getRegDate();
		this.viewCount = boardDto.getViewCount();
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public String getUserId() {
		return userId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public int getViewCount() {
		return viewCount;
	}
	
	
	
	
}
