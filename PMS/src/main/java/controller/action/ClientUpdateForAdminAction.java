package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import client.ClientDto;
import client.controller.ClientDao;
import controller.Action;

public class ClientUpdateForAdminAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		response.setCharacterEncoding("utf-8");
		System.out.println("관리자 사업자 업데이트 액션 실행");
		String clientId = request.getParameter("clientId");
		String clientPassword = request.getParameter("clientPassword");

		String clientName =  request.getParameter("clientName");
		
		String clientPhone = request.getParameter("clientPhone");
		System.out.println("사업자 전화번호(액션 확인용 출력:)" + clientPhone);
		String clientAccount = request.getParameter("clientAccount");
		String clientNumber = request.getParameter("clientNumber");
		System.out.println("사업자 번호(액션 확인용 출력:)" + clientNumber);
		ClientDao clientDao = ClientDao.getInstance();

		Client clientForDupplecheck = clientDao.findClientById(clientId);
	
		
		
		String dupplePhone = clientForDupplecheck.getClientPhone();
		String duppleAccount = clientForDupplecheck.getClientAccount();
		String duppleClientNumber = clientForDupplecheck.getClientNumber();

		//
		String error;
		//실패시 돌아갈 url링크 생성
		String link= "/clientUpdateForAdmin?clientId="+clientId;
		error = checkClientPhone(clientPhone, dupplePhone, clientDao) == true ? null : "phone";

		error = checkClientAccount(clientAccount, duppleAccount, clientDao) == true ? error : "account";

		error = checkClientNumber(clientNumber, duppleClientNumber, clientDao) == true ? error : "clientNumber";
	
		if (error == null) {
			System.out.println("사업자 수정 완료");
			ClientDto clientDto = new ClientDto(clientId, clientPassword, clientName, clientPhone, clientAccount,
					clientNumber);
			clientDao.UpdateClient(clientDto);

	
			System.out.println("해당 사업자 관리로 돌아감");
			String suc = "sucess";
			System.out.println(suc);
			request.setAttribute("adminCode", suc );
			request.getRequestDispatcher(link).forward(request, response);
			

		

		
			

			
			
			
		} else {

			System.out.println("에러 원인: " + error);
			request.setAttribute("adminCode", error);
			request.getRequestDispatcher(link).forward(request, response);
		
		
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
