package kr.co.dev.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registerServlet.do")
public class RegisterServlet extends HttpServlet {
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html; Charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        try {
	            out.println("<html>");
	            out.println("<body>");
	            out.println("<h2>회원가입</h2>");
	            out.println("<form method='post' action='/jspStudy/registerLIstServlet.do'>");
	            out.println("<table border='1' width='400'>");
	            out.println("<tr>");
	            out.println("<th width='150'>아이디</th>");
	            out.println("<td width='250'><input type='text' name='id' required></td>");
	            out.println("</tr>");
	            out.println("<tr>");
	            out.println("<th>비밀번호</th>");
	            out.println("<td><input type='password' name='pwd' required></td>");
	            out.println("</tr>");
	            out.println("<tr>");
	            out.println("<th>이메일</th>");
	            out.println("<td><input type='email' name='email' required></td>");
	            out.println("</tr>");
	            out.println("<tr>");
	            out.println("<th>이름</th>");
	            out.println("<td><input type='text' name='name' required></td>");
	            out.println("</tr>");
	            out.println("<tr>");
	            out.println("<th>생년월일</th>");
	            out.println("<td><input type='number' name='birth' placeholder='예: 20001010'></td>");
	            out.println("</tr>");
	            out.println("<tr>");
	            out.println("<td align='center' colspan='2'>");
	            out.println("<input type='submit' value='회원가입'>");
	            out.println("</td>");
	            out.println("</tr>");
	            out.println("</table>");
	            out.println("</form>");
	            out.println("</body>");
	            out.println("</html>");
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	        }
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        processRequest(request, response);
	    }
	}

