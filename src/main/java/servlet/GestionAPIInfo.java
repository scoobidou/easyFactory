package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestionAPIInfo extends HttpServlet {
	
	public static final String ATT_MESSAGES = "messages";
	public static final String VUE = "/WEB-INF/views/api_info.jsp";
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String urlString = "http://192.168.1.16:2375/info";
		
		URL url = new URL(urlString);
		
		HttpURLConnection requestUrl = (HttpURLConnection) url.openConnection();
		
		requestUrl.connect();
		
//		JsonParser jsp = new JsonParser();
//		JsonElement jse = jsp.parse(new InputStreamReader((InputStream) requestUrl.getContent()));
//		JsonObject jso = jse.getAsJsonObject();			
//		String azertyWin = jso.get("RepoDigests").getAsString();
		
		InputStream inputStream = (InputStream)requestUrl.getContent();
		
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String azertyWin = s.hasNext() ? s.next() : "";		
		
		
		request.setAttribute(ATT_MESSAGES, azertyWin);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
}
