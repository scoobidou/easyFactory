package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@WebServlet("/setupEnv")
public class SetupEnv extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE="Auto configuration";
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession httpSession = request.getSession();
		
		
		
		if (httpSession.getAttribute("user") != null){
			
			if (httpSession.getAttribute("setupEnv_step") == null){
				httpSession.setAttribute("setupEnv_step", 0);
			}
		
		request.setAttribute("title", TITLE);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}else{
            httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
            response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}
}
