package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.DAOUtils.*;

import beans.User;

public class UserDAOImpl implements UserDAO {
	
	private DAOFactory dao;
	
	UserDAOImpl(DAOFactory dao){
		this.dao = dao;
	}
	
	private static final String SQL_SELECT_BY_EMAIL = "SELECT id,email,firstName,lastName,password,inscriptionDate FROM USER WHERE email=?";

	/* Impl�mentation de la m�thode find() d�finie dans l'interface UserDAO */
	@Override
	public User find(String email) throws DAOException {
		Connection connexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			/* R�cup�ration d'une connexion depuis la factory */
			connexion = dao.getConnection();
			ps = initialisationPreparedStatement(connexion,SQL_SELECT_BY_EMAIL,false,email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = map(rs);
			}
		}catch (SQLException e){
			throw new DAOException(e);
		}finally {
			silentCloses(rs,ps,connexion);
		}
		return user;
	}
	
	private static final String SQL_INSERT = "INSERT INTO USER(email,firstName,lastName,password,inscriptionDate) VALUES (?,?,?,NOW())";
	
	/* Impl�mentation de la m�thode create() d�finie dans l'interface UserDAO */
	@Override
	public void create(User user) throws IllegalArgumentException, DAOException {
		Connection connexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connexion = dao.getConnection();
			ps = initialisationPreparedStatement(connexion,SQL_INSERT,true,user.getEmail(),user.getFirstName(),user.getLastName(),user.getPassword());
			int status = ps.executeUpdate();
			/* Analyse du statut retourn�e par la requete */
			if (status == 0 ){
				throw new DAOException("Fail to create user, 0 lines add in database");
			}
			/* R�cup�ration de l'id auto-g�n�r� par la requ�te d'insertion */
			rs = ps.getGeneratedKeys();
			if (rs.next()){
	            /* Puis initialisation de la propri�t� id du bean Utilisateur avec sa valeur */
				user.setId(rs.getLong(1));
			}else{
				throw new DAOException("Fail to create user, no generatedKeys return");
			}
		}catch (SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(rs,ps,connexion);
		}
	}
	
	/*
	 * Simple m�thode utilitaire permettant de faire le mapping entre une ligne issue de la table User et un bean User.
	 */
	private static User map(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("key"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setInscriptionDate(rs.getTimestamp("inscriptionDate"));
		return user;
	}
	
	

	
}
