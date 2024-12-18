<%@page import="java.rmi.StubNotFoundException"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
	boolean flag = (boolean)request.getAttribute("flag");
%>

<html>
<head>
<title>회원가입 확인</title></head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<br></br>
<main>
<%
if(flag){
	  out.println("<b>회원가입을 축하 드립니다.</b><br/>");
	  out.println("<a href=login.do>로그인</a>");
	}else{
	  out.println("<b>다시 입력하여 주십시오.</b><br/>");
	  out.println("<a href=regForm.do>다시 가입</a>");
	}
%>
</main>
</body>
</html>