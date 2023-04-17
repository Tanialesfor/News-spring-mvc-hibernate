package by.htp.main.service;

import by.htp.main.bean.User;

public interface UserService {
	
	String signIn(String login, String password) throws ServiceException;
//	boolean isPermission(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;
//	User getUser (String login) throws ServiceException; 
}
