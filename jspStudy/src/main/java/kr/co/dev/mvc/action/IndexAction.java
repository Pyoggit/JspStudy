package kr.co.dev.mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dev.mvc.control.ActionForward;

public class IndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("사용자 정보 받기 > 데이터 베이스 조회 > 결과 request 입력 > 화면.jsp 설정 (리다이렉트 or forward) ");
		return new ActionForward("index.jsp",false);
	}

}
