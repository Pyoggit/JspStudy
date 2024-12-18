package kr.co.dev.student.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dev.student.control.ActionForward;

public interface Action {
	//추상메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException;

}
