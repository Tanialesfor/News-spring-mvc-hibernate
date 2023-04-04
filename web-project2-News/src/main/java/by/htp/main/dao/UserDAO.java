package by.htp.main.dao;

import by.htp.main.bean.User;

public interface UserDAO  {	
	
	boolean logination(String login, String password) throws DaoException;
	boolean loginExist(String login) throws DaoException;
	boolean registration(User user) throws DaoException;
	String getRole(String login, String password) throws DaoException;
//	boolean isPermission(String login, String password) throws DaoException;
}
