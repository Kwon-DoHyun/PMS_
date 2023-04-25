package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.User;
import user.controller.UserDao;

public class FindIdAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("findId 액션 활성화");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		
		UserDao userDao = UserDao.getInstance(); 
		User user = userDao.findUserByPhoneNumber(userPhone);
		
		if(user != null && user.getUserName().equals(userName)) {
			 response.setContentType("text/html;charset=UTF-8");
			  PrintWriter out = response.getWriter();
			  out.println("<script>");
			  out.println("alert('인증성공, 회원님의 아이디는 "+ user.getUserId() +"입니다.');");
			  out.println("window.location.href='userLogin';");
			  out.println("</script>");
		} else {
			  response.setContentType("text/html;charset=UTF-8");
			  PrintWriter out = response.getWriter();
			  out.println("<script>");
			  out.println("alert('입력하신 정보가 일치하지 않습니다.');");
			  out.println("window.location.href='findId';");
			  out.println("</script>");
		}
	}

}
