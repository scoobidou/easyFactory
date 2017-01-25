package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import beans.AvaibleService;
import mybatis.MyBatisUtils;
import mybatis.mappers.AvaibleServiceMapper;

@WebServlet("/serviceShop")
public class ServiceShop extends HttpServlet {

	
	public ServiceShop(){
		super();
	}
	
	public static final String VUE = "/WEB-INF/views/serviceShop.jsp";
	public static final String TITLE = "Service Shop";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		AvaibleService avaibleService = new AvaibleService();
		List<AvaibleService> listAS = new ArrayList<>();
		
		try {
			AvaibleServiceMapper asm = sqlSession.getMapper(AvaibleServiceMapper.class);
			listAS = asm.getAllAvaibleServices();
			for (int i = 0; i < listAS.size(); i++){
				System.out.println("AS List : "+listAS.get(i).toString());
			}
		}finally{
			sqlSession.close();
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
