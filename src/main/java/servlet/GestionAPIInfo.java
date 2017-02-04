package servlet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.Container;
import beans.Host;


@WebServlet("/api_info")
public class GestionAPIInfo extends HttpServlet {
	
	public static final String ATT_MESSAGES = "messages";
	public static final String VUE = "/WEB-INF/views/api_info.jsp";
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String host = request.getParameter("docker_host");
		
		String apiUrl = "http://"+host+"/containers/json";
		
		URL url = new URL(apiUrl);
		
		Scanner scan = new Scanner(url.openStream());
		
		String containersInfo = new String();
		
		while(scan.hasNext())
			containersInfo += scan.nextLine();
		scan.close();
		
		JsonParser parser = new JsonParser();
		
		JsonElement jElement = parser.parse(containersInfo);
		JsonArray jArray = jElement.getAsJsonArray();
		
		List<Container> containerList = new ArrayList<>();
		
		for (int i = 0 ; i < jArray.size(); i++ ){
			Container container = new Container(jArray.get(i).getAsJsonObject());
			containerList.add(container);
		}
		
		apiUrl = "http://"+host+"/info";
		
		url = new URL(apiUrl);
		
		scan = new Scanner(url.openStream());
		
		String hostInfo = new String();
		
		while(scan.hasNext())
			hostInfo += scan.nextLine();
		scan.close();
		
		jElement = parser.parse(hostInfo);
		JsonObject jObject = jElement.getAsJsonObject();
		
		Host dockerHost = new Host(jObject);
		
		request.setAttribute("docker_host", dockerHost);
		request.setAttribute("containerList", containerList);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
