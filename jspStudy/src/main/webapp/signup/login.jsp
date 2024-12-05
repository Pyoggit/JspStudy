<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, javax.servlet.http.*" %>
<%@ page import="java.io.FileReader, java.io.IOException" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="./login.css">
</head>
<body>
    <form action="loginCheck.jsp" method="post"> <!-- 로그인 검증을 처리할 JSP로 폼 제출 -->
        <table>
            <thead>
                <tr>
                    <th colspan="2">로그인</th>
                </tr>
            </thead>
            <tr>
                <th class="title">아이디:</th>
                <td><label for="id"><input type="text" name="id" id="id" required></label></td>
            </tr>
            <tr>
                <th class="title">비밀번호:</th>
                <td><label for="pwd"><input type="password" name="pass" id="pwd" required></label></td>
            </tr>
        </table>

        <footer>
            <hr>
            <button type="submit">로그인</button> <!-- 로그인 검증 후 환영 페이지로 이동 -->
            <button type="button" onclick="location.href='signup.jsp'">회원가입</button> <!-- 회원가입 페이지로 이동 -->
        </footer>
    </form>
</body>
</html>
