package servlet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
		List<AvaibleService> listVersionning = new ArrayList<>();
		List<AvaibleService> listQuality = new ArrayList<>();
		List<AvaibleService> listDatabase = new ArrayList<>();
		List<AvaibleService> listDatabaseManager = new ArrayList<>();
		List<AvaibleService> listAuthentification = new ArrayList<>();
		
		try {
			AvaibleServiceMapper asm = sqlSession.getMapper(AvaibleServiceMapper.class);
			listVersionning = asm.getAllAvaibleServicesByType("Versionning");
			listQuality = asm.getAllAvaibleServicesByType("Quality");
			listDatabase = asm.getAllAvaibleServicesByType("Database");
			listDatabaseManager = asm.getAllAvaibleServicesByType("Database manager");
			listAuthentification = asm.getAllAvaibleServicesByType("Authentification");
			
			request.setAttribute("listVersionning", listVersionning);
			request.setAttribute("listQuality", listQuality);
			request.setAttribute("listDatabase", listDatabase);
			request.setAttribute("listDatabaseManager", listDatabaseManager);
			request.setAttribute("listAuthentification", listAuthentification);
		}finally{
			sqlSession.close();
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Long> serviceIdList = new ArrayList<>();
		
		serviceIdList.add(Long.valueOf(request.getParameter("versionning_service")).longValue());
		serviceIdList.add(Long.valueOf(request.getParameter("database_service")).longValue());
		serviceIdList.add(Long.valueOf(request.getParameter("databaseManager_service")).longValue());
		serviceIdList.add(Long.valueOf(request.getParameter("authentification_service")).longValue());
		 
		int qualityServiceSize = request.getParameterValues("quality_service").length;
		
		for (int i = 0 ; i < qualityServiceSize ; i++ ){
			serviceIdList.add(Long.valueOf(request.getParameterValues("quality_service")[i]).longValue());
		}
		
		String result = composeFile(serviceIdList);
		
		request.setAttribute("result", result);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
	
	
	
	public String composeFile(List<Long> serviceIdList){
		
		String versionning = null;
		String authentification = null;
		String database = null;
		String databaseManager = null;
		List<String> qualityList = new ArrayList<>();
		
		String result = null;
		SqlSession sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
		
		StringBuilder str = new StringBuilder();
		str.append("version:&nbsp;'2'<br>\n&nbsp;<br>\n&nbsp;services:<br>\n&nbsp;<br>\n");
		
		List<AvaibleService> avaibleServiceList = new ArrayList<>();
		try {
			AvaibleService avaibleService = new AvaibleService();
			AvaibleServiceMapper asm = sqlSession.getMapper(AvaibleServiceMapper.class);
			
			avaibleServiceList = asm.getAvaibleServicesById(serviceIdList);
		}
		finally{
			sqlSession.close();
		}
		
		for (AvaibleService aS: avaibleServiceList){
			if("mysql".equals(aS.getServiceName())){
				database = "mysql";
			}
			if("gitlab".equals(aS.getServiceName())){
				versionning = "gitlab";
			}
			if("ldap".equals(aS.getServiceName())){
				authentification = "ldap";
			}
			if("Sonar".equals(aS.getServiceName())){
				qualityList.add("Sonar");
			}
			if("jenkins".equals(aS.getServiceName())){
				qualityList.add("jenkins");
			}
			if("phpmyadmin".equals(aS.getServiceName())){
				databaseManager = "mysql";
			}
		}
		
		System.out.println("HELLO " +databaseManager + qualityList.toString() + authentification + versionning + database);
		
		if("mysql".equals(database) && "phpmyadmin".equals(databaseManager)){
			str.append("&nbsp;&nbsp;&nbsp;mysql:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;mysql:latest<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;mysql<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;environment:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;MYSQL_ROOT_PASSWORD=network<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8081:3306\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;volumes:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;mysql-data:/var/lib/mysql<br>\n<br>\n&nbsp;&nbsp;&nbsp;phpmyadmin:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;phpmyadmin/phpmyadmin:latest<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;phpmyadmin<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8082:80\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;links:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;mysql:db");
		}
		
		if("gitlab".equals(versionning)){
			str.append("&nbsp;&nbsp;&nbsp;gitlab:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;gitlab/gitlab-ce:latest<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;gitlab<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8083:80\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8088:443\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8089:22\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;volumes:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;gitlab-data:/var/opt/gitlab&nbsp;&nbsp;&nbsp;&nbsp;#&nbsp;For&nbsp;storing&nbsp;application&nbsp;data<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;gitlab-data:/var/log/gitlab&nbsp;&nbsp;&nbsp;&nbsp;#&nbsp;For&nbsp;storing&nbsp;logs<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;gitlab-data:/etc/gitlab&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#&nbsp;For&nbsp;storing&nbsp;the&nbsp;GitLab&nbsp;configuration&nbsp;files");
		}
		
		if(qualityList.contains("Sonar")){
			str.append("&nbsp;&nbsp;&nbsp;sonar:<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;sonarqube:latest<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;sonar<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8085:9000\"<br>&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"9092:9092\"");
		}
		
		if(qualityList.contains("jenkins") && "gitlab".equals(versionning) && qualityList.contains("Sonar")){
			str.append("&nbsp;&nbsp;&nbsp;jenkins:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;jenkins:latest<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;jenkins<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8084:8080\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"50000:50000\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;volumes:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;jenkins-data:/var/jenkins_home<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;links:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;gitlab<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;sonar");
		}
		
		str.append("&nbsp;&nbsp;&nbsp;&nbsp;tomcat:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;image:&nbsp;tomcat:latest<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;restart:&nbsp;always<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;container_name:&nbsp;tomcat<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ports:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;\"8086:8080\"<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;links:<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;jenkins<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;mysql");
		
		result = str.toString();
		
		return result;
	}
	
}
