package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import notice.NoticeDto;
import notice.controller.NoticeDao;

public class NoticeUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNumber = Integer.parseInt(request.getParameter("noticeNumber"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContents = request.getParameter("noticeContents");
		
		NoticeDao noticeDao = NoticeDao.getInstance();
		NoticeDto noticeDto = new NoticeDto(noticeNumber,noticeTitle,noticeContents);
		noticeDao.noticeUpdate(noticeDto);
		
		response.sendRedirect("notice");
		
		
	}

}
