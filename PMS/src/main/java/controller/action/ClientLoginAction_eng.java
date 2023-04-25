package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import client.controller.ClientDao;
import controller.Action;


public class ClientLoginAction_eng implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clientNumber = request.getParameter("clientNumber");
		String clientPassword = request.getParameter("clientPassword");
		System.out.println("clientNumber :"+clientNumber);
		System.out.println("clientPassword :"+clientPassword);
		
		ClientDao clientDao = ClientDao.getInstance();
		ArrayList<Client> list = clientDao.getClientAll();
		HttpSession session = null;
		boolean check = false;
		for(Client client : list) {
			if(client.getClientNumber().equals(clientNumber) && client.getClientPassword().equals(clientPassword)) {
				session = request.getSession();
				session.setAttribute("log", client);
				session.setAttribute("category", "client");
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
			  out.println("alert('Please check your number or password again.');");
			  out.println("window.location.href='clientLogin';");
			  out.println("</script>");
		}
	}

}
