package servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@WebServlet("/setupEnv")
public class SetupEnv extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE="Auto configuration";
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		request.setAttribute("title", TITLE);
		
		try {
			JSch jsch = new JSch();
			
			String host = null;
			
			host = "manu@192.168.1.22";
			
			String user = host.substring(0,host.indexOf('@'));
			host = host.substring(host.indexOf('@')+1);
			
			Session session = jsch.getSession(user,host,22);		
			
			String password = null;
			
			password ="azerty";
			
			session.setPassword(password);
			
			session.connect();
			
			String command = null;
			
			// command = "";
			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			
			channel.setInputStream(null);
			
			((ChannelExec)channel).setErrStream(System.err);
		      InputStream in=channel.getInputStream();

		      channel.connect();

		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
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
		      System.out.println(e);
		    }

		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	//TODO: Tester cette partie avec un docker configuré - Puis voir comment faire un scp - Et enfin voir comment lancer l'installation automatique du truc
}
