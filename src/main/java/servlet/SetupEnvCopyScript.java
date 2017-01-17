package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

@WebServlet("/setupEnv_copyScript")
public class SetupEnvCopyScript extends HttpServlet {
	
	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE="Auto configuration"; 
	
	public SetupEnvCopyScript(){
		super();
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		HttpSession httpSession = request.getSession();
		
		if (httpSession.getAttribute("user")!=null){
			
			FileInputStream fis=null;
			
			try {
				JSch jsch = new JSch();
				
				String host = null;
				
				host = "manu@192.168.1.13";
				
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
				String remoteFile = "/home/manu/test.sh";
				boolean ptimestamp = true;
				
				command = "scp " + (ptimestamp ? "-p" :"") +" -t "+remoteFile;
				Channel channel = session.openChannel("exec");
				((ChannelExec)channel).setCommand(command);
				
				channel.setInputStream(null);
				
				OutputStream out=channel.getOutputStream();
		        InputStream in=channel.getInputStream();

			    channel.connect();
			    
		        if(checkAck(in)!=0){
		        	httpSession.setAttribute("setupEnv_step", -2);
		        	response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		        	return;
    	        }
		        
		        String scriptPath = getServletConfig().getServletContext().getRealPath("/")+"scripts/test.sh";
		        File scriptFile = new File(scriptPath);
		        
		        scriptFile.setExecutable(true);
		        
		        if(ptimestamp){
		            command="T"+(scriptFile.lastModified()/1000)+" 0";
		            // The access time should be sent here,
		            // but it is not accessible with JavaAPI ;-<
		            command+=(" "+(scriptFile.lastModified()/1000)+" 0\n"); 
		            out.write(command.getBytes()); out.flush();
		            if(checkAck(in)!=0){
		            	httpSession.setAttribute("setupEnv_step", -2);
			        	response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
			        	return;
		            }
		        }
		        
		        // send "C0644 filesize filename", where filename should not include '/'
		        long filesize=scriptFile.length();
		        command="C0644 "+filesize+" ";
		        if(scriptPath.lastIndexOf('/')>0){
		          command+=scriptPath.substring(scriptPath.lastIndexOf('/')+1);
		        }
		        else{
		          command+=scriptPath;
		        }
		        command+="\n";
		        out.write(command.getBytes()); out.flush();
		        if(checkAck(in)!=0){
		        	httpSession.setAttribute("setupEnv_step", -2);
		        	response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		        	return;		        
		        }

		        // send a content of lfile
		        fis=new FileInputStream(scriptPath);
		        byte[] buf=new byte[1024];
		        while(true){
		          int len=fis.read(buf, 0, buf.length);
		  	if(len<=0) break;
		          out.write(buf, 0, len); //out.flush();
		        }
		        fis.close();
		        fis=null;
		        // send '\0'
		        buf[0]=0; out.write(buf, 0, 1); out.flush();
		        if(checkAck(in)!=0){
		        	httpSession.setAttribute("setupEnv_step", -2);
		        	response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		        	return;
		        }
		        out.close();

		        channel.disconnect();
		        session.disconnect();
		        httpSession.setAttribute("setupEnv_info","Copy of file : success");
		        httpSession.setAttribute("setupEnv_step", 2);

		      }
		      catch(Exception e){
		    	httpSession.setAttribute("setupEnv_error", e.getMessage());
		        try{if(fis!=null)fis.close();}catch(Exception ee){}
		        
		      }
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		}
		else{
			httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
            response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}
	
	static int checkAck(InputStream in) throws IOException{
	    int b=in.read();
	    // b may be 0 for success,
	    //          1 for error,
	    //          2 for fatal error,
	    //          -1
	    if(b==0) return b;
	    if(b==-1) return b;

	    if(b==1 || b==2){
	      StringBuffer sb=new StringBuffer();
	      int c;
	      do {
		c=in.read();
		sb.append((char)c);
	      }
	      while(c!='\n');
	      if(b==1){ // error
		System.out.print(sb.toString());
	      }
	      if(b==2){ // fatal error
		System.out.print(sb.toString());
	      }
	    }
	    return b;
	  }
	
}
