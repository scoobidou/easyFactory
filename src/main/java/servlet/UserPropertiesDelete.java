package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import beans.User;
import mybatis.MyBatisUtils;
import mybatis.mappers.UserPropertiesMapper;

@WebServlet("/userPropertiesDelete")
public class UserPropertiesDelete extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/userProperties.jsp";
	public static final String TITLE = "User Properties";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		HttpSession httpSession = request.getSession();
		User user = (User)httpSession.getAttribute("user");
		Long userId = user.getId();
		try {
			UserPropertiesMapper upm = sqlSession.getMapper(UserPropertiesMapper.class);
			beans.UserProperties userP = upm.getUserPropertiesByUser(userId);
			
			httpSession.setAttribute("userProperties", userId);
			System.out.println(userP.toString());
			
		}finally{
			sqlSession.close();
		}
		response.sendRedirect(request.getContextPath()+"/userProperties");
	}
}
