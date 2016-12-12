package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

	private static final String FICHIER_PROPERTIES = "dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER = "user";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url;
	private String user;
	private String password;
	
	DAOFactory(String url, String user, String password){
		this.url=url;
		this.user=user;
		this.password=password;
	}
	
	/*
	 * M�thode charg�e de r�cup�rer les informations de connexion � la base de donn�es, charger le driver et retourner une instance
	 * de la Factory
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		
		Properties properties = new Properties();
		String url;
		String driver;
		String user;
		String password;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		
		if(fichierProperties == null){
			throw new DAOConfigurationException("File "+ FICHIER_PROPERTIES + "not found");
		}
		
		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			user = properties.getProperty(PROPERTY_USER);
			password = properties.getProperty(PROPERTY_PASSWORD);
		}catch (IOException e){
			throw new DAOConfigurationException("Impossible to load properties file "+ FICHIER_PROPERTIES,e);
		}
		
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			throw new DAOConfigurationException("Driver not found in classpath",e);
		}
		
		DAOFactory instance = new DAOFactory(url,user,password);
		return instance;
	}
	
	/*
	 * M�thode charg�e de fournir une connexion � la bdd
	 */
	Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	
	/*
	 * M�thode de r�cup�ration de l'impl�mentation des diff�rentes DAO
	 */
	public UserDAO getUserDAO(){
		return new UserDAOImpl(this);
	}
}
