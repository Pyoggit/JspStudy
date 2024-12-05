<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>환영합니다</title>
</head>
<body>
    <%
        // JSP에서 자동으로 제공되는 session 내장 객체 사용
        String id = (session != null) ? (String) session.getAttribute("id") : null;

        // 세션이 존재하고 아이디가 있는 경우 환영 메시지를 출력
        if (id != null) {
    %>
        <h2><%= id %>님, 환영합니다!</h2>
        <a href="logout.jsp">로그아웃</a> <!-- 상대 경로 사용 -->
    <%
        } else {
            // 세션이 없거나 아이디가 없는 경우 로그인 페이지로 리다이렉트
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
