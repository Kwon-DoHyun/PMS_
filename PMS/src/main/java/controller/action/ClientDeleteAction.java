package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import client.controller.ClientDao;
import controller.Action;

public class ClientDeleteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("사업자 삭제 액션 실행");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");

		ClientDao clientDao = ClientDao.getInstance();

		Client client = clientDao.findClientById(clientId);

		if (clientPassword.equals(client.getClientPassword())) {

			if (clientDao.deleteClientById(clientId)) {
				System.out.println("삭제완료");
				HttpSession session = request.getSession();
				session.removeAttribute("log");
				session.removeAttribute("category");
		
				request.setAttribute("modify", "deleteClient");
				request.getRequestDispatcher("/index").forward(request, response);
				
			}else {
				System.out.println("삭제 오류가 있음. 외래 키나 테이블 오류 가능성 체크");
				request.setAttribute("errorCode", "table");
				request.getRequestDispatcher("/clientDelete").forward(request, response);
			}

		} else {

			request.setAttribute("errorCode", "password");
			request.getRequestDispatcher("/clientDelete").forward(request, response);
		}

	}
}