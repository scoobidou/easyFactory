package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import mybatis.MyBatisUtils;
import mybatis.mappers.UserPropertiesMapper;
import security.SecurityUtils;

@WebServlet("/userProperties")
public class UserProperties extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/userProperties.jsp";
	public static final String TITLE = "User Properties";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		beans.UserProperties userP = new beans.UserProperties();
		try {
		
		String password = SecurityUtils.encrypt(request.getParameter("ssh_password"));
		userP.setSshPassword(password);
		}catch(Exception e){
			return;
		}
		userP.setIdUser(Long.valueOf(request.getParameter("id_user")).longValue());
		userP.setSshHost(request.getParameter("ssh_host"));
		
		userP.setSshUser(request.getParameter("ssh_user"));
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();

		try {
			UserPropertiesMapper userPMapper = sqlSession.getMapper(UserPropertiesMapper.class);
			userPMapper.insertUserProperties(userP);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		response.sendRedirect(request.getContextPath()+"/userProperties");
	}

}
