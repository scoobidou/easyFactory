package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tutoDocker_2")

public class TutoDocker2 extends HttpServlet {

	public static final String TITLE="TutoDocker2";	
	
	public static final String VUE = "/WEB-INF/views/tutoDocker_2.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("title", TITLE);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}