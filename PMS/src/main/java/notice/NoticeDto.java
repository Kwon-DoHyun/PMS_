package notice;

import java.sql.Timestamp;

public class NoticeDto {
	private int noticeNumber;
	private String noticeTitle;
	private String noticeContents;
	private String clientId;
	private Timestamp regDate;
	private int viewCount;
	
	public NoticeDto(int noticeNumber,String noticeTitle,String noticeContents,String clientId,Timestamp regDate,int viewCount ) {
		this.noticeNumber = noticeNumber;
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
		this.clientId = clientId;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}
	
	// 공지 작성 (board_title , board_contents)
	public NoticeDto(String noticeTitle, String noticeContents) {
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
		
	}
	// 공지 업데이트(제목, 내용, 게시글 번호 )
	public NoticeDto(int noticeNumber,String noticeTitle,String noticeContents) {
		this.noticeNumber = noticeNumber;
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
