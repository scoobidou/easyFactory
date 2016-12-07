package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GestionAPIInfo extends HttpServlet {
	
	public static final String ATT_MESSAGES = "messages";
	public static final String VUE = "/WEB-INF/views/api_info.jsp";
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String urlString = "http://192.168.1.16:2375/info";
		
		URL url = new URL(urlString);
		
		HttpURLConnection requestUrl = (HttpURLConnection) url.openConnection();
		
		requestUrl.connect();
		
//		InputStream inputStream = (InputStream)requestUrl.getContent();
//		
//		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//		String azertyWin = s.hasNext() ? s.next() : "";	
		
		JsonParser parser = new JsonParser();
		JsonElement jse = parser.parse(new InputStreamReader((InputStream) requestUrl.getContent()));
		JsonObject jso = jse.getAsJsonObject();	
		
		String azertyWin = jso.toString();
		String blabla = jso.get("Containers").getAsString();
		
//		s.close();	
		
		request.setAttribute(ATT_MESSAGES, azertyWin);
		request.setAttribute("info",blabla);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
}
