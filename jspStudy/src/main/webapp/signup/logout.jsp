<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%
    // 세션 무효화
    if (session != null) {
        session.invalidate();
    }

    // 로그인 페이지로 리다이렉트
    String loginPage = "login.jsp"; // 현재 파일과 같은 디렉토리에 있는 경우 상대 경로 사용
    response.sendRedirect(loginPage);
%>
