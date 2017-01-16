package servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import beans.User;
import mybatis.MyBatisUtils;
import mybatis.mappers.UserMapper;

@WebServlet("/login")
public class Login extends HttpServlet {

	public static final String VUE = "/WEB-INF/views/user_space.jsp";
	public static final String TITLE="Login";
	
	public Login(){
		super();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		request.setAttribute("title", TITLE);
		
		HttpSession session = request.getSession();
		
		User user = new User();
		
		boolean validationFailed = false;
		
		if (!Pattern.compile("^.+@.+\\..+$").matcher(request.getParameter("user_mail")).matches()){
			validationFailed = true;
		}
		if (validationFailed){
			response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
			return;
		}
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.getUserByMail(request.getParameter("user_mail"));
        }finally{
            sqlSession.close();
        }
		
        //On vérifie que le mot de passe crypté correspond au mot de passe donné
        if(user != null && BCrypt.checkpw(request.getParameter("user_password"), user.getPassword())){

            //Si oui, on stock les infos de l'utilisateur dans la session
            user.setPassword(null);
            session.setAttribute("user", user);

            //On redirige vers l'accueil
            String from = (String) session.getAttribute("from");
            if(from == null || "".equals(from)){
                response.sendRedirect(request.getContextPath() + response.encodeRedirectURL("/home"));
            }else{
                response.sendRedirect(request.getContextPath() + response.encodeRedirectURL(from));
            }
            return;
        }else{
            //Sinon, on renvoi vers le login avec un message d'erreur
            request.setAttribute("loginError", "Email or password incorrect.");
            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        }
		
	}
}
