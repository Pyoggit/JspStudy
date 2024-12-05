<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="./signup.css">
</head>
<body>
    <%
        // 사용자가 폼을 제출했을 때만 데이터베이스 작업 수행
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String message = "";

        if (id != null && pwd != null && email != null && name != null) {
            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                // db.properties 파일 경로 설정
                String filePath = "C:\\dev\\jspWorkspace\\jspStudy\\src\\main\\resources\\db.properties";
                Properties pt = new Properties();
                try (FileReader reader = new FileReader(filePath)) {
                    pt.load(reader);
                } catch (IOException e) {
                    throw new ServletException("db.properties 파일을 찾을 수 없습니다: " + e.getMessage());
                }

                String url = pt.getProperty("url");
                String user = pt.getProperty("user");
                String pw = pt.getProperty("pw");

                // Oracle JDBC 드라이버 로드
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // 데이터베이스 연결 및 INSERT 쿼리 실행
                con = DriverManager.getConnection(url, user, pw);
                pstmt = con.prepareStatement("INSERT INTO SIGNUP (ID, PWD, EMAIL, NAME, BIRTH) VALUES (?, ?, ?, ?, ?)");
                pstmt.setString(1, id);
                pstmt.setString(2, pwd);
                pstmt.setString(3, email);
                pstmt.setString(4, name);
                if (birth != null && !birth.isEmpty()) {
                    pstmt.setInt(5, Integer.parseInt(birth));
                } else {
                    pstmt.setNull(5, java.sql.Types.INTEGER);
                }

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    message = "회원가입이 성공적으로 완료되었습니다. 잠시 후 로그인 페이지로 이동합니다.";
                    // 5초 후 로그인 페이지로 리다이렉트
                    out.println("<meta http-equiv='refresh' content='5;url=login.jsp'>");
                } else {
                    message = "회원가입에 실패했습니다. 다시 시도해주세요.";
                }
            } catch (Exception e) {
                message = "회원가입 중 오류 발생: " + e.getMessage();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    message = "데이터베이스 연결 닫기 중 오류 발생: " + e.getMessage();
                }
            }
        }
    %>

    <form action="signup.jsp" method="post">
        <table>
            <thead>
                <tr>
                    <th colspan="2">회원 기본 정보</th>
                </tr>
            </thead>
            <tr>
                <th class="title">아이디:</th>
                <td><input type="text" name="id" id="id" required><i>4~12자의 영문 대소문자와 숫자로만 입력</i></td>
            </tr>
            <tr>
                <th class="title">비밀번호:</th>
                <td><input type="password" name="pwd" id="pwd" required><i>4~12자의 영문 대소문자와 숫자로만 입력</i></td>
            </tr>
            <tr>
                <th class="title">비밀번호확인:</th>
                <td><input type="password" name="pwdtest" id="pwdtest" required></td>
            </tr>
            <tr>
                <th class="title">메일주소:</th>
                <td><input type="text" name="email" id="email" required><i>ex) abcd@gmail.com</i></td>
            </tr>
            <tr>
                <th class="title">이름:</th>
                <td><input type="text" name="name" id="name" required></td>
            </tr>
            <tr>
                <th class="title">생년월일:</th>
                <td><input type="text" name="birth" id="birth"><i>ex) 20000101</i></td>
            </tr>
        </table>

        <footer>
            <hr>
            <button type="submit">회원 가입</button>
            <button type="reset">다시 입력</button>
        </footer>
    </form>

    <% if (!message.isEmpty()) { %>
        <p style="color: red;"><%= message %></p>
    <% } %>
</body>
</html>
