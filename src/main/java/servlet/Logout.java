package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")

public class Logout extends HttpServlet {
	
	public Logout(){
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + response.encodeRedirectUrl("/home"));
	}
}
