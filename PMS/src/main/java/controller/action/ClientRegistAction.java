package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.ClientDto;
import client.controller.ClientDao;
import controller.Action;


public class ClientRegistAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("유저 가입 액션 실행");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");
		String clientName = request.getParameter("clientName");
		String clientPhone = request.getParameter("clientPhone");
		String clientAccount = request.getParameter("clientAccount");
		String clientNumber  = request.getParameter("clientNumber ");

		
		ClientDao clientDao = ClientDao.getInstance();
		// id 중복 체크
		if (clientDao.duppleCheckedById(clientId)) {
			// 전화번호 중복 체크
			if (clientDao.duppleCheckedByClientPhone(clientPhone)) {
				if (clientDao.duppleCheckedByClientAccount(clientAccount)) {
					if (clientDao.duppleCheckedByClientNumber(clientNumber)){

						ClientDto clientDto = new ClientDto(clientId, clientPassword, clientName, clientPhone, clientAccount,clientNumber );
						clientDao.createClient(clientDto);

						System.out.println("유저 가입 완료");
						response.setContentType("text/html;charset=UTF-8");
						 PrintWriter out = response.getWriter();
						  out.println("<script>");
						  out.println("alert('사업자 회원가입 완료.');");
						  out.println("window.location.href='/clientLogin';");
						  out.println("</script>");
						
					}else {
						System.out.println("사업자번호가 중복되어 생성 불가");
						request.setAttribute("dupple", "clientNumber");
						request.getRequestDispatcher("/clientRegist").forward(request, response);
					}
				 
					
					

				} else {
					System.out.println("계좌가 중복되어 생성 불가");
					request.setAttribute("dupple", "account");
					request.getRequestDispatcher("/clientRegist").forward(request, response);
				}

			} else {
				System.out.println("전화번호가 중복되어 생성 불가");
				request.setAttribute("dupple", "phone");
				request.getRequestDispatcher("/clientRegist").forward(request, response);
			}

		} else {
			System.out.println("아이디가 중복되어 생성 불가");
			request.setAttribute("dupple", "id");
			request.getRequestDispatcher("/clientRegist").forward(request, response);

		}
		
	}
}
