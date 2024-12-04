package kr.co.dev.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/registerLIstServlet.do")
public class RegisterLIstServlet extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; Charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");

		// 데이터베이스 연결 및 INSERT 쿼리 수행 (JDBC 코드 예시)
		try {
		    // Oracle JDBC 드라이버를 로드
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    
		    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webuser", "123456");
		    PreparedStatement pstmt = con.prepareStatement("INSERT INTO REGISTER (ID, PWD, EMAIL, NAME, BIRTH) VALUES (?, ?, ?, ?, ?)");

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
                // 회원가입 성공 시 세션 생성 및 로그인 처리
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("pass", pwd);

                // 로그인 후 로그인 완료 페이지로 리다이렉트
                response.sendRedirect("/jspStudy/loginServlet.do");
            } else {
                // 회원가입 실패 시 에러 페이지
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("<h2>회원가입에 실패했습니다. 다시 시도해주세요.</h2>");
                out.println("<a href='/jspStudy/registerServlet.do'>회원가입 다시하기</a>");
                out.println("</body>");
                out.println("</html>");
                out.close();
            }
		} catch (ClassNotFoundException e) {
		    throw new ServletException("JDBC 드라이버 로드 중 오류 발생: " + e.getMessage());
		} catch (SQLException e) {
		    throw new ServletException("회원가입 중 오류 발생: " + e.getMessage());
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
