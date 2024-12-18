package kr.co.dev.student.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dev.student.control.ActionForward;
import kr.co.dev.student.model.StudentDAO;
import kr.co.dev.student.model.StudentVO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentDAO dao = StudentDAO.getInstance();
		StudentVO svo = new StudentVO();
		String id = request.getParameter("id");
		svo.setId(id);
		boolean check = dao.selectIdCheck(svo);
		
		
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		return new ActionForward("/mvcmem/idCheck.jsp", false);
	}

}
