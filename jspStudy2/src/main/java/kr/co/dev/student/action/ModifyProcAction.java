package kr.co.dev.student.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dev.student.control.ActionForward;
import kr.co.dev.student.model.StudentDAO;
import kr.co.dev.student.model.StudentVO;

public class ModifyProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		StudentDAO dao = StudentDAO.getInstance();
		StudentVO vo = new StudentVO(id, 
			request.getParameter("pass"),
			request.getParameter("name"), 
			request.getParameter("phone1"),
			request.getParameter("phone2"), 
			request.getParameter("phone3"),
			request.getParameter("email"), 
			request.getParameter("zipcode"),
			request.getParameter("address1"),
			request.getParameter("address2"));
		boolean flag = dao.updateDB(vo);
		request.setAttribute("flag", flag);
		return new ActionForward("/mvcmem/modifyProc.do", false);
	}

}
