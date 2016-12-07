package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

public class Test extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		String paramAuteur = request.getParameter("auteur");
		String message = "Transmission de la variable OK ! "+ paramAuteur;
		
		User user = new User();
		
		user.setFirstName("Emmanuel");
		user.setLastName("Croccel");
		user.setEmail("emmanuel.croccel@outlook.fr");
		user.setPassword("azerty");
		
		request.setAttribute("test", message);
		request.setAttribute("user", user);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/test.jsp" ).forward( request, response );
		
	}
	
}
