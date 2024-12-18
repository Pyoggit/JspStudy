package kr.co.dev.student.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.do")
public class MemControlServlet extends HttpServlet {
    
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//사용자 정보를 가져온다.
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"));
		System.out.println("cmd = " + cmd);

		
		if(cmd != null) {
			ActionFactory factory = ActionFactory.getInstance();
			kr.co.dev.student.action.Action action = factory.getAction(cmd);
			
			if(action != null) {
				ActionForward af = action.execute(request, response);
				if(af.isRedirect() == true) {
					response.sendRedirect(af.getUrl());
				}else {
					RequestDispatcher rd = request.getRequestDispatcher(af.getUrl());
					rd.forward(request, response);
				}
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);		
			}
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

}
