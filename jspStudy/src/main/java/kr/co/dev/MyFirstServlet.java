package kr.co.dev;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "myFirstServlet", urlPatterns = { "/myFirstServlet" })
@WebServlet ("/myFirstServlet" )
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//디폴트 생성자
    public MyFirstServlet() {
        super();
        System.out.println("처음으로 한번만 초기화");
    }

    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    	//super.service(request,response);
    
    }


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서비스 실행");
		//1.사용자 정보 가져오기 
		//2.데이터베이스 연결 및 분석 후 DB CRUD 
		
		
		
		//3.화면을 만들어서 웹서버로 전달
		//서버에서 클라이언트 문자를 보내기 위한 문자set
		response.setCharacterEncoding("UTF-8");
		//브라우저에게 (text/html charset = UTF-8)
		response.setContentType("text/html charset = UTF-8");
		//출력스트림
		PrintWriter out = response.getWriter();
		Date date = new Date();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>My First Servlet Program !!!</h1>");
        out.println("<br>");
        out.println(date.toString());
        out.println("</body>");
        out.println("</html>");
        out.flush();
	}

}
