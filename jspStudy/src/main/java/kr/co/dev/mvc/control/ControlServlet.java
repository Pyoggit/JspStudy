package kr.co.dev.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dev.mvc.action.Action;


@WebServlet("*.do")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//사용자 정보를 가져온다.
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"));
		System.out.println("cmd = " + cmd);

		
		if(cmd != null) {
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd);
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
