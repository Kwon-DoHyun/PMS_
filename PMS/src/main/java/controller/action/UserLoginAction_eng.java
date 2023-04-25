package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import user.User;
import user.controller.UserDao;

public class UserLoginAction_eng implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		System.out.println("userId :"+userId);
		System.out.println("userPassword :"+userPassword);
		UserDao userDao = UserDao.getInstance();
		ArrayList<User> list = userDao.getUserAll();
		HttpSession session = null;
		boolean check = false;
		for(User user : list) {
			if(user.getUserId().equals(userId) && user.getUserPassword().equals(userPassword)) {
				session = request.getSession();
				session.setAttribute("log", user);
				session.setAttribute("category", "user");
				check = true;
			}
		}
		if(check) {
			System.out.println("로그인성공");
			response.sendRedirect("/index_eng");
			
		}else {
			// 로그인 실패시
			  response.setContentType("text/html;charset=UTF-8");
			  // 실패시 여기서 alert창 띄움
			  PrintWriter out = response.getWriter();
			  out.println("<script>");
			  out.println("alert(' Please check your ID or password again.');");
			  out.println("window.location.href='userLogin';");
			  out.println("</script>");
		}
		
	}

}
