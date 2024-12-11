<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="kr.co.dev.memberone.StudentDAO"%>
<%@ page import="kr.co.dev.memberone.StudentVO"%>

<%
request.setCharacterEncoding("utf-8");
StudentDAO sdao = new StudentDAO();
StudentVO svo = new StudentVO();
%>

<%
boolean flag = sdao.insertDB(svo);
%>


<html>
<head>
<title>회원가입 확인</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body bgcolor="#FFFFCC">
	<br></br>
	<main>
		<%
		if (flag) {
			out.println("<b>회원가입을 축하 드립니다.</b><br/>");
			out.println("<a href=login.jsp>로그인</a>");
		} else {
			out.println("<b>다시 입력하여 주십시오.</b><br/>");
			out.println("<a href=regForm.jsp>다시 가입</a>");
		}
		%>
	</main>
</body>
</html>