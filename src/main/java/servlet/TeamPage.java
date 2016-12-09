package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeamPage extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/team.jsp";
	public static final String TITLE="Team";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setAttribute("title", TITLE);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
	
}
