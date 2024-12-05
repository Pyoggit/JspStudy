package kr.co.dev.login;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
            
			// DB에서 확인한다
			boolean isValidUser = false;
			Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
			
			// Oracle JDBC 드라이버 로드
            try {
                // Oracle JDBC 드라이버 로드
                Class.forName("oracle.jdbc.driver.OracleDriver");

                con = DriverManager.getConnection(url, user, pw);
                pstmt = con.prepareStatement("SELECT * FROM REGISTER WHERE ID = ? AND PWD = ?");
                pstmt.setString(1, id);
                pstmt.setString(2, pass);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    // 사용자가 존재하면 유효한 사용자로 설정
                    isValidUser = true;
                }
            } catch (ClassNotFoundException e) {
                throw new ServletException("JDBC 드라이버 로드 중 오류 발생: " + e.getMessage());
            } catch (Exception e) {
                throw new ServletException("로그인 중 오류 발생: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    throw new ServletException("데이터베이스 리소스 닫기 중 오류 발생: " + e.getMessage());
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
