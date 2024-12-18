package kr.co.dev.student.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dev.student.control.ActionForward;
import kr.co.dev.student.model.StudentDAO;
import kr.co.dev.student.model.StudentVO;

public class LoginProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		StudentDAO dao = StudentDAO.getInstance();
		StudentVO vo = new StudentVO();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		vo.setId(id);
		vo.setPass(pass);
		int check = dao.selectLoginCheck(vo);
		if (check == 1) {	
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
			request.setAttribute("check", check);
		return new ActionForward("login.do", false);
	}

}
