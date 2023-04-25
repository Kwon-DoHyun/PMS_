package notice;

import java.sql.Timestamp;

public class Notice {
	private int noticeNumber;
	private String noticeTitle;
	private String noticeContents;
	private String clientId;
	private Timestamp regDate;
	private int viewCount;
	
	public Notice(int noticeNumber,String noticeTitle,String noticeContents,Timestamp regDate,int viewCount ) {
		this.noticeNumber = noticeNumber;
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}
	
	public Notice(NoticeDto noticeDto) {
		this.noticeNumber = noticeDto.getNoticeNumber();
		this.noticeTitle = noticeDto.getNoticeTitle();
		this.noticeContents = noticeDto.getNoticeContents();
		this.clientId = noticeDto.getClientId();
		this.regDate = noticeDto.getRegDate();
		this.viewCount = noticeDto.getViewCount();
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public String getClientId() {
		return clientId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public int getViewCount() {
		return viewCount;
	}
	
	
	
	
}
