package by.htp.main.dao;

import by.htp.main.bean.User;

public interface UserDAO  {	
	
	User logination(String login, String password) throws DaoException;
	boolean loginExist(String login) throws DaoException;
	boolean registration(User user) throws DaoException;
//	public String getRole(String login, String password) throws DaoException;
	
}
