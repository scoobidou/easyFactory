package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Example extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/example.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("setupEnv_step") == null) {
			httpSession.setAttribute("setupEnv_step", 0);
		}

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession httpSession = request.getSession();

		if (request.getParameter("ssh_user") != null) {
			httpSession.setAttribute("ssh_user", request.getParameter("ssh_user"));
		}
		if (request.getParameter("ssh_host") != null) {
			httpSession.setAttribute("ssh_host", request.getParameter("ssh_host"));
		}
		if (request.getParameter("ssh_password") != null) {
			httpSession.setAttribute("ssh_password", request.getParameter("ssh_password"));
		}

		if (request.getParameter("ssh_password") != null && request.getParameter("ssh_host") != null
				&& request.getParameter("ssh_user") != null
				&& httpSession.getAttribute("setupEnv_step").toString().equals("0")) {
			httpSession.setAttribute("setupEnv_step", 1);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

		if (httpSession.getAttribute("setupEnv_step").equals("")) {
			httpSession.setAttribute("setupEnv_step", 0);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else if (httpSession.getAttribute("setupEnv_step").toString().equals("5")) {
			httpSession.setAttribute("setupEnv_step", 6);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/example");

		} else if (httpSession.getAttribute("setupEnv_step").toString().equals("4")) {

			httpSession.setAttribute("setupEnv_step", 5);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/example");

		} else if (httpSession.getAttribute("setupEnv_step").toString().equals("3")) {

			httpSession.setAttribute("setupEnv_step", 4);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/example");

		} else if (httpSession.getAttribute("setupEnv_step").toString().equals("2")) {

			httpSession.setAttribute("setupEnv_step", 3);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/example");

		} else if (httpSession.getAttribute("setupEnv_step").toString().equals("1")) {
			httpSession.setAttribute("setupEnv_step", 2);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/example");
		}
	}
}
