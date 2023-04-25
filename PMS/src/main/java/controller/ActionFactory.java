package controller;

import controller.action.BoardDeleteAction;
import controller.action.BoardWriterAction;
import controller.action.BoardWriterAction_Eng;
import controller.action.BoradUpdateAction;
import controller.action.CheckPaymentAction;
import controller.action.ClientDeleteAction;
import controller.action.ClientGetResultAction;
import controller.action.ClientLoginAction;
import controller.action.ClientLoginAction_eng;
import controller.action.ClientRegistAction;
import controller.action.ClientUpdateAction;
import controller.action.ClientUpdateForAdminAction;
import controller.action.FindIdAction;
import controller.action.FindPasswordAction;
import controller.action.LogoutAction;
import controller.action.LogoutAction_Eng;
import controller.action.NoticeDeleteAction;
import controller.action.NoticeUpdateAction;
import controller.action.NoticeWriterAction;
import controller.action.PreReserVation;
import controller.action.PreReserVation_Eng;
import controller.action.SearchBoardAction;
import controller.action.SearchBookingAction;
import controller.action.SearchLocationAction;
import controller.action.SearchSpotDetailAction;
import controller.action.UpdateCreditAction;
import controller.action.UserBookingAction;
import controller.action.UserBookingAction_Eng;
import controller.action.UserDeleteAction;
import controller.action.UserLoginAction;
import controller.action.UserLoginAction_eng;
import controller.action.UserRegistAction;
import controller.action.UserUpdateAction;
import controller.action.UserUpdateForAdminAction;
import controller.action.nonUserBookingAction;

public class ActionFactory {
	private ActionFactory() {
	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {

		Action action = null;
		System.out.println("command: " + command);
		if (command.equals("preReservation")) {
			action = new PreReserVation();
		} else if (command.equals("preReservation_eng")) {
			action = new PreReserVation_Eng();
		} else if (command.equals("searchLocation")) {
			action = new SearchLocationAction();
		}else if (command.equals("UserRegist")) {
			action = new UserRegistAction();
		} else if (command.equals("userDelete")) {
			action = new UserDeleteAction();
		} else if (command.equals("userUpdate")) {
			action = new UserUpdateAction();
		} else if (command.equals("ClientRegist")) {
			action = new ClientRegistAction();
		} else if (command.equals("boardWriter")) {
			action = new BoardWriterAction();
		} else if (command.equals("boardWriter_eng")) {
			action = new BoardWriterAction_Eng();
		}else if (command.equals("boardUpdate")) {
			action = new BoradUpdateAction();
		} else if (command.equals("boardDelete")) {
			action = new BoardDeleteAction();
		} else if (command.equals("noticeWriter")) {
			action = new NoticeWriterAction();
		} else if (command.equals("noticeUpdate")) {
			action = new NoticeUpdateAction();
		} else if (command.equals("noticeDelete")) {
			action = new NoticeDeleteAction();
		} else if (command.equals("clientUpdate")) {
			action = new ClientUpdateAction();
		} else if (command.equals("userLogin")) {
			action = new UserLoginAction();
		} else if (command.equals("userLogin_eng")) {
			action = new UserLoginAction_eng();
		} else if (command.equals("clientLogin")) {
			action = new ClientLoginAction();
		} else if (command.equals("clientLogin_eng")) {
			action = new ClientLoginAction_eng();
		}else if (command.equals("logout")) {
			action = new LogoutAction();
		}else if (command.equals("logout_eng")) {
			action = new LogoutAction_Eng();
		} else if (command.equals("searchBoard")) {
			action = new SearchBoardAction();
		} else if (command.equals("findPassword")) {
			action = new FindPasswordAction();
		} else if (command.equals("findId")) {
			action = new FindIdAction();
		} else if (command.equals("clientDelete")) {
			action = new ClientDeleteAction();
		} else if (command.equals("searchSpot")) {
			action = new SearchSpotDetailAction();
		} else if (command.equals("userBooking")) {
			action = new UserBookingAction();
		} else if (command.equals("userBooking_eng")) {
			action = new UserBookingAction_Eng();
		} else if (command.equals("nonUserBooking")) {
			action = new nonUserBookingAction();
		} else if (command.equals("checkPayment")) {
			action = new CheckPaymentAction();
		} else if (command.equals("userUpdateForAdmin")) {
			action = new UserUpdateForAdminAction();
		} else if (command.equals("clientUpdateForAdmin")) {
			action = new ClientUpdateForAdminAction();
		} else if (command.equals("updateCredit")) {
			action = new UpdateCreditAction();
		} else if (command.equals("clientGetResult")) {
			action = new ClientGetResultAction();
		} else if (command.equals("nonUserSearchBooking")) {
			action = new SearchBookingAction();
		} 


		return action;
	}
}
