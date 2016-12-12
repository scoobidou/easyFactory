package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import forms.InscriptionForm;

public class Signin extends HttpServlet{
	
	public static final String CONF_DAO_FACTORY = "dao";
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";
	public static final String VIEW = "/WEB-INF/views/signinPage.jsp";
	
	private UserDAO userDAO;
	
	public void init() throws ServletException {
		/* Récupération de l'instance de notre DAO User */
		this.userDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDAO();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request,response);
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		/* Preparation de la page d'inscription */
		InscriptionForm form = new InscriptionForm(userDAO);
		
		/* Traitement de la requete et récupération du bean existant*/
		User user = form.signUser(request);
		
		/* Stockage du formulaire et du bean dans l'objet request*/
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, user);
		
		this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
	}
	
}
