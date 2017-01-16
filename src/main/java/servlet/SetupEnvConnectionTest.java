package servlet;

import java.io.IOException;
import java.io.InputStream;

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


@WebServlet("/setupEnv_connectionTest")
public class SetupEnvConnectionTest extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE="Auto configuration";
	
	public SetupEnvConnectionTest(){
		super();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession httpSession = request.getSession();
		
		if (httpSession.getAttribute("user") != null){
		
		try {
			JSch jsch = new JSch();
			
			String host = null;
			
			host = "manu@192.168.1.22";
			
			String user = host.substring(0,host.indexOf('@'));
			host = host.substring(host.indexOf('@')+1);
			
			Session session = jsch.getSession(user,host,22);		
			
			//UNSECURE - TODO : Change this shit
			session.setConfig("StrictHostKeyChecking", "no");
			
			String password = null;
			
			password ="network";
			
			session.setPassword(password.getBytes());
			
			session.connect();
			
			String command = null;
			
			command = "ls";
			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			
			channel.setInputStream(null);
			
			((ChannelExec)channel).setErrStream(System.err);
		      InputStream in=channel.getInputStream();

		      channel.connect();
		      httpSession.setAttribute("setupEnv_connectionInfo","Connection to "+host+" established");
		      httpSession.setAttribute("setupEnv_step", 1);

		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		          System.out.print(channel.getExitStatus());
		        }
		        if(channel.isClosed()){
		          if(in.available()>0) continue; 
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		      channel.disconnect();
		      session.disconnect();
		    }
		    catch(Exception e){
		      boolean test = e.getCause().toString().contains("java.net.ConnectException");
		      if (test){
		    	  httpSession.setAttribute("setupEnv_connectionError", "Unable to connect : "+e.getMessage());
		    	  httpSession.setAttribute("setupEnv_step", -1);
		      }
		    }

		
		response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		}else{
            httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
            response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}

}
