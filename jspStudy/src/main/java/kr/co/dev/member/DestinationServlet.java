package kr.co.dev.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/destinationServlet.do")
public class DestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = (String)request.getAttribute("name");
		//1.페이지 이동방식 결정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; Charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		try {
			out.println("<html>");
			 out.println("<head>");
			 out.println("<title>Destination</title>"); 
			 out.println("</head>");
			 out.println("<body>");
			 out.println("<h1> Destination Servlet 입니다</h1>");
			 out.println("<h1> name = " + name + "</h1>");
			 out.println("</body>");
			 out.println("</html>");
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			if(out != null) {
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

