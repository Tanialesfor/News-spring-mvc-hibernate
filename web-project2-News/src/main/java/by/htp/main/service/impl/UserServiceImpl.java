package by.htp.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.main.bean.User;
import by.htp.main.dao.DaoException;
import by.htp.main.dao.UserDAO;
import by.htp.main.dao.impl.UserDAOImpl;
import by.htp.main.service.ServiceException;

import by.htp.main.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
   
	@Override
	public String signIn(String login, String password) throws ServiceException {
				
			try {
				if(userDAO.logination(login, password)) {
					return userDAO.getRole(login, password);
				}else {
					return "guest";
				}
			} catch(DaoException e) {
				throw new ServiceException("error from method signIn Service", e);
			}
	}

	@Override
	public boolean registration(User user) throws ServiceException {

		    try {
				if (userDAO.registration(user)) {
					return true;
				} else {
					return false;
				}
			} catch (DaoException e) {
				throw new ServiceException("error from method registration Service", e);
			}
	}

//	@Override
//	public boolean isPermission(String login, String password) throws ServiceException {
//		
//		UserValidationBuilder userValidationBuilder = new UserValidator.UserValidationBuilder();
//		UserValidator validator = userValidationBuilder.checkAUthData(login, password).isValid();
//
//		if (!validator.getErrors().isEmpty()) {
//			throw new ServiceException(validator.getErrors().toString());
//		}
//		
//		else {
//			try {
//				if (userDAO.isPermission(login, password)==true) {
//					return true;
//				}
//			} catch (DaoException e) {
//				throw new ServiceException("error from method isPermission Service", e);
//			}
//			return false;
//		}
//	}
}
