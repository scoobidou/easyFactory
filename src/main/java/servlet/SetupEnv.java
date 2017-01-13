package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setupEnv")
public class SetupEnv extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE="Auto configuration";
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		request.setAttribute("title", TITLE);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
