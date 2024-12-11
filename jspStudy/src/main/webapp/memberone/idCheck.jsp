<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.co.dev.memberone.StudentDAO"%>
<%@page import="kr.co.dev.memberone.StudentVO"%>
	
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");

StudentVO svo = new StudentVO();
svo.setId(id);
StudentDAO sdao = new StudentDAO();
boolean flag = sdao.selectIdCheck(svo);
%>



<!DOCTYPE html>
<html>
<head>
<title>ID중복체크</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
	<br>
	<center>
		<b><%=id%></b>
		<%
		if (flag == true) {
			out.println("는 이미 존재한는 ID입니다.<br></br>");
		} else {
			out.println("는 사용 가능 합니다.<br></br>");
		}
		%>
		<hr>
		<button onClick="javascript:self.close()">닫기</button>
	</center>
</body>
</html>