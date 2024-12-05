<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>멤버 변수 사용 <%= declation %></h1>
	<h1>멤버 함수 사용 <%= getDeclation() %> </h1>
	<h1>연산처리</h1>
	<%
		//객체종류: context, config, request, response, pageContext, session, out, page
		//자바연산처리(제어문, 반복문)
		String message = "연산처리문입니다."; //message: 서비스에 있는 지역변수
		out.println("내장객체를 이용한 출력" + message);
	%>
	<h1>멤버변수선언</h1>
	<%!
		String declation = "멤버변수선언문.";
	%>
	<h1>멤버함수선언</h1>
	<%!
		public String getDeclation(){
			return declation;
	}
		%>
</body>
</html>