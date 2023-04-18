package by.htp.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.main.bean.User;
import by.htp.main.dao.DaoException;
import by.htp.main.dao.UserDAO;
import by.htp.main.dao.impl.UserDAOImpl;
import by.htp.main.service.ServiceException;

import by.htp.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public User signIn(String login, String password) throws ServiceException {
		User user = null;
		try {
			user = userDAO.logination(login, password);

		} catch (DaoException e) {
			throw new ServiceException("error from method signIn Service", e);
		}
		return user;
	}

	@Transactional
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

}
