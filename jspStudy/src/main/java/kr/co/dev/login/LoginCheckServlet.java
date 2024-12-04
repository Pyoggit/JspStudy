package kr.co.dev.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginCheck.do" )
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 사용자 정보를 읽는다
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			// DB에서 확인한다
			boolean isValidUser = false;
			
			// Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webuser", "123456");
                 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM REGISTER WHERE ID = ? AND PWD = ?")) {

                pstmt.setString(1, id);
                pstmt.setString(2, pass);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        // 사용자가 존재하면 유효한 사용자로 설정
                        isValidUser = true;
                    }
                }
            }

            // 로그인 되면 세션을 만들어서 저장한다
            if (isValidUser) {
                // 세션이 있으면 가져오고, 세션이 없으면 생성
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("pass", pass);
            }

            // 로그인 화면으로 리다이렉트
            response.sendRedirect("/jspStudy/loginServlet.do");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("로그인 중 오류 발생: " + e.getMessage());
        }
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
		
	}
	

}
