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

import org.apache.ibatis.session.SqlSession;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import beans.User;
import mybatis.MyBatisUtils;
import mybatis.mappers.UserPropertiesMapper;

@WebServlet("/setupEnv")
public class SetupEnv extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/setupEnv.jsp";
	public static final String TITLE = "Auto configuration";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("user") != null) {
			if (!"reset".equals(request.getParameter("reset_button"))) {
				SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();

				beans.UserProperties userProperties = new beans.UserProperties();

				try {
					UserPropertiesMapper userPropMapper = sqlSession.getMapper(UserPropertiesMapper.class);
					User user = (User) httpSession.getAttribute("user");
					userProperties = userPropMapper.getUserPropertiesByUser(user.getId());
					httpSession.setAttribute("ssh_host", userProperties.getSshHost());
					httpSession.setAttribute("ssh_user", userProperties.getSshUser());
					httpSession.setAttribute("ssh_password", userProperties.getSshPassword());
				} finally {
					sqlSession.close();
				}
				if (userProperties != null) {
					httpSession.setAttribute("setupEnv_step", 1);
				}

				if (httpSession.getAttribute("setupEnv_step") == null) {
					httpSession.setAttribute("setupEnv_step", 0);
				}
			} else {
				httpSession.setAttribute("setupEnv_step", 0);
				if (httpSession.getAttribute("ssh_host") != null && httpSession.getAttribute("ssh_password") != null
						&& httpSession.getAttribute("ssh_host") != null) {
					httpSession.setAttribute("ssh_host", null);
					httpSession.setAttribute("ssh_user", null);
					httpSession.setAttribute("ssh_password", null);
					
					SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();

					beans.UserProperties userProperties = new beans.UserProperties();

					try {
						UserPropertiesMapper userPropMapper = sqlSession.getMapper(UserPropertiesMapper.class);
						User user = (User) httpSession.getAttribute("user");
						userProperties = userPropMapper.getUserPropertiesByUser(user.getId());
						System.out.println(userProperties.toString());
						userPropMapper.deleteUserProperties(userProperties.getId());
						sqlSession.commit();
					} finally {
						sqlSession.close();
					}
				}
			}

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("user") != null) {

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

				System.out.println(request.getParameter("ssh_checkbox"));

				if ("remember".equals(request.getParameter("ssh_checkbox"))) {
					// TODO: INSERT THIS SHIT HERE
				}

				httpSession.setAttribute("setupEnv_step", 1);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
			}

			if (httpSession.getAttribute("setupEnv_step").equals("")) {
				httpSession.setAttribute("setupEnv_step", 0);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
			} else if (httpSession.getAttribute("setupEnv_step").toString().equals("5")) {
				httpSession.setAttribute("setupEnv_step", 6);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
				// response.sendRedirect(request.getContextPath() + "/example");

			} else if (httpSession.getAttribute("setupEnv_step").toString().equals("4")) {

				httpSession.setAttribute("setupEnv_step", 5);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
				// response.sendRedirect(request.getContextPath() + "/example");

			} else if (httpSession.getAttribute("setupEnv_step").toString().equals("3")) {
				remoteAction(httpSession, "./", httpSession.getAttribute("ssh_host").toString(),
						httpSession.getAttribute("ssh_password").toString(),
						httpSession.getAttribute("ssh_user").toString());
				httpSession.setAttribute("setupEnv_step", 4);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
				// response.sendRedirect(request.getContextPath() + "/example");

			} else if (httpSession.getAttribute("setupEnv_step").toString().equals("2")) {
				remoteAction(httpSession, "scp", httpSession.getAttribute("ssh_host").toString(),
						httpSession.getAttribute("ssh_password").toString(),
						httpSession.getAttribute("ssh_user").toString());
				httpSession.setAttribute("setupEnv_step", 3);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
				// response.sendRedirect(request.getContextPath() + "/example");

			} else if (httpSession.getAttribute("setupEnv_step").toString().equals("1")) {
				remoteAction(httpSession, "ls", httpSession.getAttribute("ssh_host").toString(),
						httpSession.getAttribute("ssh_password").toString(),
						httpSession.getAttribute("ssh_user").toString());
				httpSession.setAttribute("setupEnv_step", 2);
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
				return;
				// response.sendRedirect(request.getContextPath() + "/example");
			}

		} else {
			httpSession.setAttribute("loginError", "You must be logged to view this resource : Automatic setup");
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
		}
	}

	public void remoteAction(HttpSession httpSession, String command, String host, String password, String user) {
		// Initialisation des paramètres.
		try {
			JSch jsch = new JSch();

			System.out.println(host);
			System.out.println(password);
			Session session = jsch.getSession(user, host, 22);
			// Unsecure TODO: change it
			session.setConfig("StrictHostKeyChecking", "no");

			session.setPassword(password.getBytes());
			session.connect();

			boolean ptimestamp = false;
			FileInputStream fis = null;

			if ("scp".equals(command)) {
				String remoteFile = "/home/manu/test.sh";
				ptimestamp = true;
				command = "scp " + (ptimestamp ? "-p" : "") + " -t " + remoteFile;
			}
			if ("./".equals(command)) {
				command = "chmod 755 test.sh; dos2unix test.sh; ./test.sh" + host + " " + password + "  init";
			}

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);

			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			// Command block for scp
			if (command.contains("scp")) {
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
			else {
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
		} catch (Exception e) {
			boolean test = e.getCause().toString().contains("java.net.ConnectException");
			if (test) {
				httpSession.setAttribute("setupEnv_error", "Unable to connect : " + e.getMessage());
				httpSession.setAttribute("setupEnv_step", -1);
			} else {
				httpSession.setAttribute("setupEnv_error", "Unable to connect : " + e.getMessage());
			}
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
}
