package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {

	
	/* Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requete SQL et les objets donnés.
	 */
	public static PreparedStatement initialisationPreparedStatement(Connection connexion, String sql, boolean returnGeneratedKeys, Object...objects) throws SQLException {
		
		PreparedStatement ps = connexion.prepareStatement(sql,returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0 ; i < objects.length; i++){
			ps.setObject(i+1, objects[i]);
		}
		return ps;
	} 
	 
	/*
	 * Fermeture des ressources 
	 */
	
	/* Fermeture silencieuse du resultset */
	public static void silentClose( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Fail to close ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse du statement */
	public static void silentClose( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Fail to close Statement : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse de la connexion */
	public static void silentClose( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Fail to close Connection : " + e.getMessage() );
	        }
	    }
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void silentCloses( Statement statement, Connection connexion ) {
	    silentClose( statement );
	    silentClose( connexion );
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void silentCloses( ResultSet resultSet, Statement statement, Connection connexion ) {
		silentClose( resultSet );
		silentClose( statement );
		silentClose( connexion );
	}
	
}
