package kr.co.dev.student.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dev.student.control.ActionForward;
import kr.co.dev.student.model.StudentDAO;
import kr.co.dev.student.model.ZipCodeVO;

public class ZipCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		StudentDAO dao = StudentDAO.getInstance();
		ZipCodeVO zvo = new ZipCodeVO();
		String check = request.getParameter("check");
		String dong = request.getParameter("dong");
		zvo.setDong(dong);
		ArrayList<ZipCodeVO> zipcodeList = dao.selectZipCode(zvo);
		int totalList = zipcodeList.size();
		request.setAttribute("check", check);
		request.setAttribute("dong", dong);
		request.setAttribute("zipcodeList", zipcodeList);
		request.setAttribute("totalList", totalList);
		return new ActionForward("/mvcmem/zipCheck.jsp",false);
	}

}
