package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import beans.User;
import mybatis.MyBatisUtils;
import mybatis.mappers.UserMapper;


public class Login extends HttpServlet {

	public Login(){
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		
		request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = new User();
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		try{
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			user = userMapper.getByMail(request.getParameter("user_mail"));
			
		}finally{
			sqlSession.close();
		}
		
		// Check if user password is correct.
		if (user!= null && BCrypt.checkpw(request.getParameter("user_password"), user.getPassword())){
			
			user.setPassword(null);
			session.setAttribute("user",user);
			
			// Redirection to home page
			String from = (String) session.getAttribute("from");
			if (from==null || "".equals(from)){
				response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/"));
			}else {
				response.sendRedirect(request.getContextPath() + response.encodeRedirectURL(from));
			}
			return;
		}else{
			request.setAttribute("loginError", "Username or password wrong");
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		}
		
	}
	
}
