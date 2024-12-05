<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%!
	//멤버변수,멤버함수,오버라이딩
	private int globalNumber = 0;
	//멤버함수
	public int getGlobalNumber(){
		return this.globalNumber;
	}
	//오버라이딩
	public void jspInit(){
		System.out.println("jspInit() 오버라이딩 된 함수 호출");
	}
	public void jspDestroy(){
		System.out.println("jspDestroy() 오버라이딩 된 함수 호출");
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//자바코드 실행문(제어문,반복문)
	//지역변수
	int localNumber = 0;
	localNumber ++;
	this.globalNumber ++;
%>
	<ul>
		<li>glabalNumber = <%= this.getGlobalNumber() %></li>
		<li>localNumber = <%= localNumber %></li>
		<li></li>
	</ul>
</body>
</html>