package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import user.User;
import userAccount.controller.UserAccountDao;

public class UpdateCreditAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("금액 충전 메소드 ");
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("log");
		String userAccount = request.getParameter("userAccount");
		String creditTamp = request.getParameter("credit");
		creditTamp = creditTamp.replace(",", "");
		int credit = Integer.parseInt(creditTamp);
		int plusCredit = Integer.parseInt(request.getParameter("plusCredit"));
		credit += plusCredit;
		System.out.println("충전 후 금액:"+credit);
		
		UserAccountDao userAccountDao = UserAccountDao.getInstance();
		userAccountDao.creditUpdate(userAccount,credit);
		response.getWriter().write(String.valueOf(credit));
		
	}

}
