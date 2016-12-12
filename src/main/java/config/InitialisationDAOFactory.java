package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.DAOFactory;

public class InitialisationDAOFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "dao";
	
	private DAOFactory dao;
	
	@Override
	public void contextInitialized(ServletContextEvent event){
		/* R�cup�ration du ServletContext lors du chargement de l'application */
		ServletContext sc = event.getServletContext();
		/* Instanciation de la factory */
		this.dao = DAOFactory.getInstance();
		/* Enregistrement dans un attribut ayant pour port�e toute l'application */
		sc.setAttribute(ATT_DAO_FACTORY, this.dao);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event){
		/* Rien a r�aliser lors de la fermeture de l'application */
	}
	
}
