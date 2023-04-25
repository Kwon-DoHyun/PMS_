package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import client.ClientDto;
import client.controller.ClientDao;
import controller.Action;
import user.User;
import user.UserDto;

public class ClientUpdateAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setCharacterEncoding("utf-8");
		System.out.println("사업자 업데이트 액션 실행");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");

		String clientName =  request.getParameter("clientName");
		
		String clientPhone = request.getParameter("clientPhone");
		String clientAccount = request.getParameter("clientAccount");
		String clientNumber = request.getParameter("clientNumber");
		System.out.println("쿨러" + clientNumber);
		ClientDao clientDao = ClientDao.getInstance();

		Client clientForDupplecheck = clientDao.findClientById(clientId);
	
		
		
		String dupplePhone = clientForDupplecheck.getClientPhone();
		String duppleAccount = clientForDupplecheck.getClientAccount();
		String duppleClientNumber = clientForDupplecheck.getClientNumber();

		//
		String error;
		//전화번호 검사 진행(오류 발생시 오류코드 생성)
		error = checkClientPhone(clientPhone, dupplePhone, clientDao) == true ? null : "phone";
		//계좌번호 검사 진행(오류 발생시 오류코드 생성)
		error = checkClientAccount(clientAccount, duppleAccount, clientDao) == true ? error : "account";
		//사업자번호 검사 진행(오류 발생시 오류코드 생성)
		error = checkClientNumber(clientNumber, duppleClientNumber, clientDao) == true ? error : "clientNumber";
		//오류가 발생하지 않을 경우 업데이트 진행
		if (error == null) {
			System.out.println("사업자 수정 시작");
			ClientDto clientDto = new ClientDto(clientId, clientPassword, clientName, clientPhone, clientAccount,
					clientNumber);
			clientDao.UpdateClient(clientDto);

			Client client = new Client(clientDto);
		
			HttpSession session = request.getSession();
			session.setAttribute("log", client);
			System.out.println("마이페이지로 돌아감");
			String suc = "sucess";
			System.out.println(suc);
			request.setAttribute("modify",suc );
			request.getRequestDispatcher("/clientMyPage").forward(request, response);
			
			
		} else {
			System.out.println("에러 원인: " + error);
			request.setAttribute("dupple", error);
			request.getRequestDispatcher("/clientUpdate").forward(request, response);
		}

	}

	public boolean checkClientPhone(String clientPhone, String dupplePhone, ClientDao clientDao) {

		if (!dupplePhone.equals(clientPhone)) {

			if (clientDao.duppleCheckedByClientPhone(clientPhone)) {
				return true;
			} else {
				return false;
			}

		}

		return true;

	}

	public boolean checkClientAccount(String clientAccount, String duppleAccount, ClientDao clientDao) {

		if (!duppleAccount.equals(clientAccount)) {

			if (clientDao.duppleCheckedByClientAccount(clientAccount)) {
				return true;
			} else {
				return false;
			}

		}

		return true;

	}

	public boolean checkClientNumber(String clientNumber, String duppleClientNumber, ClientDao clientDao) {

		if (!duppleClientNumber.equals(clientNumber)) {

			if (clientDao.duppleCheckedByClientNumber(clientNumber)) {
				return true;
			} else {
				return false;
			}

		}

		return true;

	}

}
