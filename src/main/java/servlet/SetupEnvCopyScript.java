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
	public static final String TITLE = "Auto configuration";

	public SetupEnvCopyScript() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("user") != null) {

			FileInputStream fis = null;

			try {
				JSch jsch = new JSch();

				String host = null;

				host = "manu@192.168.1.13";

				String user = host.substring(0, host.indexOf('@'));
				host = host.substring(host.indexOf('@') + 1);

				Session session = jsch.getSession(user, host, 22);

				// UNSECURE - TODO : Change this shit
				session.setConfig("StrictHostKeyChecking", "no");

				String password = null;

				password = "network";

				session.setPassword(password.getBytes());

				session.connect();

				String command = null;
				String remoteFile = "/home/manu/test.sh";
				boolean ptimestamp = true;

				command = "scp " + (ptimestamp ? "-p" : "") + " -t " + remoteFile;
				Channel channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);

				channel.setInputStream(null);

				OutputStream out = channel.getOutputStream();
				InputStream in = channel.getInputStream();

				channel.connect();

				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
					return;
				}

				String scriptPath = getServletConfig().getServletContext().getRealPath("/") + "scripts/test.sh";
				File scriptFile = new File(scriptPath);

				if (ptimestamp) {
					command = "T" + (scriptFile.lastModified() / 1000) + " 0";
					// The access time should be sent here,
					// but it is not accessible with JavaAPI ;-<
					command += (" " + (scriptFile.lastModified() / 1000) + " 0\n");
					out.write(command.getBytes());
					out.flush();
					if (checkAck(in) != 0) {
						httpSession.setAttribute("setupEnv_step", -2);
						response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
						return;
					}
				}

				// send "C0644 filesize filename", where filename should not
				// include '/'
				long filesize = scriptFile.length();
				command = "C0644 " + filesize + " ";
				if (scriptPath.lastIndexOf('/') > 0) {
					command += scriptPath.substring(scriptPath.lastIndexOf('/') + 1);
				} else {
					command += scriptPath;
				}
				command += "\n";
				out.write(command.getBytes());
				out.flush();
				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
					return;
				}

				// send a content of lfile
				fis = new FileInputStream(scriptPath);
				byte[] buf = new byte[1024];
				while (true) {
					int len = fis.read(buf, 0, buf.length);
					if (len <= 0)
						break;
					out.write(buf, 0, len); // out.flush();
				}
				fis.close();
				fis = null;
				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();
				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
					return;
				}
				out.close();

				channel.disconnect();
				session.disconnect();
				httpSession.setAttribute("setupEnv_info", "Copy of file : success");
				httpSession.setAttribute("setupEnv_step", 2);

			} catch (Exception e) {
				httpSession.setAttribute("setupEnv_error", e.getMessage());
				try {
					if (fis != null)
						fis.close();
				} catch (Exception ee) {
				}

			}
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/setupEnv"));
		} else {
			httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}

	static int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}
	/*
	 * Need to add control during different step to catch errors.
	 * Code 1 : first step succes / -1 : connectivity error
	 * Code 2 : second step sucess / -2 : error during scp
	 * Code 3 : script execution success / -3 : error during execution
	 * Code 4 : Container docker creation success / -4 : error during creation
	 * Code 5 : Remote access to API success / -5 : Remote access to API error
	 */
	protected void remoteAction(HttpSession httpSession, String command, String host, String password, String user){
		// Initialisation des paramètres.
		try {
			JSch jsch = new JSch();
			
			host = user + "@" + host;
			
			Session session = jsch.getSession(user, host, 22);
			//Unsecure TODO: change it
			session.setConfig("StrictHostKeyChecking", "no");
			
			session.setPassword(password.getBytes());
			session.connect();
			
			boolean ptimestamp = false;
			FileInputStream fis = null;
			
			if("scp".equals(command)){
				String remoteFile = "/home/manu/test.sh";
				ptimestamp = true;
				command = "scp " + (ptimestamp ? "-p" : "") + " -t " + remoteFile;
			}
			if("./".equals(command)){
				command = "chmod 755 test.sh; dos2unix test.sh; ./test.sh 192.168.1.13 init";
			}
			
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);

			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();
			
			// Command block for scp
			if(command.contains("scp")){
				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					return;
				}

				String scriptPath = getServletConfig().getServletContext().getRealPath("/") + "scripts/test.sh";
				File scriptFile = new File(scriptPath);

				if (ptimestamp) {
					command = "T" + (scriptFile.lastModified() / 1000) + " 0";
					// The access time should be sent here,
					// but it is not accessible with JavaAPI ;-<
					command += (" " + (scriptFile.lastModified() / 1000) + " 0\n");
					out.write(command.getBytes());
					out.flush();
					if (checkAck(in) != 0) {
						httpSession.setAttribute("setupEnv_step", -2);
						return;
					}
				}

				// send "C0644 filesize filename", where filename should not
				// include '/'
				long filesize = scriptFile.length();
				command = "C0644 " + filesize + " ";
				if (scriptPath.lastIndexOf('/') > 0) {
					command += scriptPath.substring(scriptPath.lastIndexOf('/') + 1);
				} else {
					command += scriptPath;
				}
				command += "\n";
				out.write(command.getBytes());
				out.flush();
				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					return;
				}

				// send a content of lfile
				fis = new FileInputStream(scriptPath);
				byte[] buf = new byte[1024];
				while (true) {
					int len = fis.read(buf, 0, buf.length);
					if (len <= 0)
						break;
					out.write(buf, 0, len); // out.flush();
				}
				fis.close();
				fis = null;
				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();
				if (checkAck(in) != 0) {
					httpSession.setAttribute("setupEnv_step", -2);
					return;
				}
				out.close();
			}
			// Command block for test connection & script execution.
			else{
				byte[] tmp = new byte[1024];
				while (true) {
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						if (i < 0)
							break;
						System.out.print(new String(tmp, 0, i));
					}
					if (channel.isClosed()) {
						if (in.available() > 0)
							continue;
						System.out.println("exit-status: " + channel.getExitStatus());
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (Exception ee) {
					}
				}
			}
			channel.disconnect();
			session.disconnect();
		}catch(Exception e){
			boolean test = e.getCause().toString().contains("java.net.ConnectException");
			if (test) {
				httpSession.setAttribute("setupEnv_error", "Unable to connect : " + e.getMessage());
				httpSession.setAttribute("setupEnv_step", -1);
			}else{
				httpSession.setAttribute("setupEnv_error", "Unable to connect : " + e.getMessage());
			}
		}
	}

}
