package dao;

import beans.User;

public interface UserDAO {
	
	void create(User user) throws DAOException;
	User find(String email) throws DAOException;


}
