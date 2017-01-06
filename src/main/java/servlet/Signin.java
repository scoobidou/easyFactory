package servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import beans.User;
import mybatis.MyBatisUtils;
import mybatis.mappers.UserMapper;



@WebServlet("/home")
public class Signin extends HttpServlet{
	
	public Signin(){
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		
		request.getRequestDispatcher("WEB-INF/views/home.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		
		//TODO : INSERT GET SESSION
		
		boolean validationFailed = false;
		
		if(!request.getParameter("user_password").equals(request.getParameter("user_confirmation"))){
			validationFailed = true;
			// ERROR MESSAGE SESSION
		}
		if (request.getParameter("user_password").length() < 8 ){
			validationFailed=true;
		}
		if (!Pattern.compile("^.+@.+\\..+$").matcher(request.getParameter("user_mail")).matches()){
			validationFailed = true;
		}
		if (validationFailed){
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
			return;
		}
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		// Preparation de l'objet à stocker 
		
		User user = new User();
		
		user.setEmail(request.getParameter("user_mail"));
		user.setFirstName(request.getParameter("user_first_name"));
		user.setLastName(request.getParameter("user_last_name"));
		user.setPassword(BCrypt.hashpw(request.getParameter("user_password"), BCrypt.gensalt()));
		
		try {
			// Insert user
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insert(user);
			
			sqlSession.commit();
		}finally{
			sqlSession.close();
		}
		
		response.sendRedirect(request.getContextPath() + response.encodeRedirectUrl("/home"));
	}
	
}
