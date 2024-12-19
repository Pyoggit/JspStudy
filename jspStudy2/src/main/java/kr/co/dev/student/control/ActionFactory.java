package kr.co.dev.student.control;

import kr.co.dev.student.action.Action;
import kr.co.dev.student.action.DeleteFormAction;
import kr.co.dev.student.action.DeleteProcAction;
import kr.co.dev.student.action.IdCheckAction;
import kr.co.dev.student.action.IndexAction;
import kr.co.dev.student.action.LoginFormAction;
import kr.co.dev.student.action.LoginProcAction;
import kr.co.dev.student.action.LogoutAction;
import kr.co.dev.student.action.ModifyFormAction;
import kr.co.dev.student.action.ModifyProcAction;
import kr.co.dev.student.action.RegFormAction;
import kr.co.dev.student.action.RegProcAction;
import kr.co.dev.student.action.ZipCheckAction;

//싱글톤
public class ActionFactory {
	private static ActionFactory factory;

	public static synchronized ActionFactory getInstance() {
		if (factory == null) {
			factory = new ActionFactory();
		}

		return factory;
	}

	private ActionFactory() {
	}

	public Action getAction(String cmd) {
		Action action = null;
		switch (cmd) {
		
		case "/index.do":
			action = new IndexAction();
			break;
		case "/regForm.do":
			action = new RegFormAction();
			break;
		case "/idCheck.do":
			action = new IdCheckAction();
			break;
		case "/zipCheck.do":
			action = new ZipCheckAction();
			break;
		case "/regProc.do":
			action = new RegProcAction();
			break;
		case "/login.do":
			action = new LoginFormAction();
			break;
		case "/loginProc.do":
			action = new LoginProcAction();
			break;
		case "/logout.do":
			action = new LogoutAction();
			break;
		case "/modifyForm.do":
			action = new ModifyFormAction();
			break;
		case "/modifyProc.do":
			action = new ModifyProcAction();
			break;
		case "/deleteForm.do":
			action = new DeleteFormAction();
			break;
		case "/deleteProc.do":
			action = new DeleteProcAction();
			break;
			

		default:
			action = new IndexAction();
			break;
			
		}
		return action;
	}

}
