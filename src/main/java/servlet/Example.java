package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/views/example.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			
			
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			
		}

}