<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%


	Date date = new Date();


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>top.jsp 페이지 내용</h1>
	<h2>오늘의 시간은 <%= date.toString() %></h2>
	
	
</body>
</html>