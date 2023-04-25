package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import notice.NoticeDto;
import notice.controller.NoticeDao;

public class NoticeWriterAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContents = request.getParameter("noticeContents");
		
	
		NoticeDao noticeDao = NoticeDao.getInstance();
		NoticeDto noticeDto = new NoticeDto(noticeTitle, noticeContents);
		noticeDao.writerByNewNotice(noticeDto);
		
		response.sendRedirect("notice");
		
	}

}
