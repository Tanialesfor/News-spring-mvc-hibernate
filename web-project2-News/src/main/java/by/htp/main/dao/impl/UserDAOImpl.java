package by.htp.main.dao.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import by.htp.main.bean.News;
import by.htp.main.bean.Role;
import by.htp.main.bean.StatusUser;
import by.htp.main.bean.User;
import by.htp.main.dao.DaoException;
import by.htp.main.dao.UserDAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {	
	
//	private static final String SELECT_PASSWORD_FROM_LOGIN = "SELECT password FROM users WHERE login = ?";
//	private static final String SELECT_ID_FROM_LOGIN = "SELECT id FROM users WHERE login = ?";
//	private static final String SELECT_ROLE_NAME_FROM_LOGIN = "SELECT role_name FROM news.roles INNER JOIN news.users ON news.roles.id=news.users.roles_id WHERE login = ?";
//	private static final String SELECT_PERMISSION_NAME_FROM_PREMID_LOGIN = "SELECT permission_name FROM news.permissions INNER JOIN news.role_has_permissions ON news.permissions.id=news.role_has_permissions.permissions_id \r\n"
//	+ "INNER JOIN news.roles ON news.roles.id=news.role_has_permissions.roles_id \r\n"
//	+ "INNER JOIN news.users ON news.roles.id=news.users.roles_id WHERE news.permissions.id=? AND news.users.login=?";
//	private static final String INSERT_USERS = "INSERT INTO users(login, password, date_registration, roles_id, status_id) VALUES(?, ?, ?, ?, ?)";
//	private static final String INSERT_USERS_DETAILS = "INSERT INTO user_details(users_id, name, surname, birthday, email) VALUES(?, ?, ?, ?, ?)";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static int workload = 12;

	public static String hashPassword(String passwordPlaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashedPassword = BCrypt.hashpw(passwordPlaintext, salt);

		return(hashedPassword);
	}

	public static boolean checkPassword(String passwordPlaintext, String storedHash) {
		boolean passwordVerified = false;

		//From https://en.wikipedia.org/wiki/Bcrypt
		//The bcrypt function is the default password hash algorithm for BSD and other systems including some Linux distributions such as SUSE Linux.[2] 
		//The prefix "$2a$" or "$2b$" (or "$2y$") in a hash string in a shadow password file indicates that hash string is a bcrypt hash in modular crypt format.[3] 
		//The rest of the hash string includes the cost parameter, a 128-bit salt (base-64 encoded as 22 characters), and 
		//184 bits of the resulting hash value (base-64 encoded as 31 characters).[4] 
		//The cost parameter specifies a key expansion iteration count as a power of two, which is an input to the crypt algorithm.
		
		if(null == storedHash || !storedHash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		passwordVerified = BCrypt.checkpw(passwordPlaintext, storedHash);

		return(passwordVerified);
	}
	
	@Override
	public User logination(String login, String password) throws DaoException {

		User user;
         try{
			Session currentSession = sessionFactory.getCurrentSession();
	
			Query<User> theQuery = currentSession.createQuery("from User where login = :loginParam", User.class);
			theQuery.setParameter("loginParam", login);
	
			user = theQuery.uniqueResult();
			if (user != null && checkPassword(password, user.getPassword())==true) {
				return user;
			}	
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method logination", e);
		}
		return null;
	}
	
	public User getUser(String login) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			User user=currentSession.get(User.class, login);
		return user;
		
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method getUser", e);
		}
	}
	
//    @Override
// 	public boolean logination(String login, String password) throws DaoException {
//    	
//    	try {
//				if (checkPassword(password, getUser(login).getPassword())==true) {
//					return true;
//				}
//							
//		} catch (HibernateException e) {
//			throw new DaoException("Hibernate getting error from method logination", e);
//		}
//    	return false;
//        }
     
	@Override
	public boolean loginExist(String login) throws DaoException {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			if (currentSession.get(User.class, login) != null)
				return true;
			
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method loginExist", e);
		}
		
		return false;
	}    
    
	@Override
	public boolean registration(User user) throws DaoException {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where login = :loginParam", User.class);
		theQuery.setParameter("loginParam", user.getLogin());
		         
		try {
			User theUser = theQuery.uniqueResult();

			if (theUser != null) {
				return false;
			} else {				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime date = LocalDateTime.now();
				String dateTime = formatter.format(date);
				String hashpassword = hashPassword(user.getPassword());
				user.setPassword(hashpassword);
				user.setDateRegistration(dateTime);
				user.setRole(new Role(2));
				user.setStatusUser(new StatusUser(1));
	
				currentSession.save(user);
				return true;
			}
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method registration",e);
		}
	}
	
//	@Override
//	public boolean registration(User user) throws DaoException {
//		
//		try {
//			Session currentSession = sessionFactory.getCurrentSession();
//			    if (loginExist (user.getLogin())==false) {
//			currentSession.save(user);
//			Query theQuery = currentSession
//					.createQuery("update User set password=:passwordParam, role.id=:roleParam, dateRegistration=:dateParam, statusUser.id=:statusUserParam  where id=:idParam", User.class);
//
//			theQuery.setParameter("passwordParam", hashPassword(user.getPassword())); 
//			theQuery.setParameter("roleParam", 2); // user
//			Date now = new Date();
//			theQuery.setParameter("dateParam", now.getTime());
//			theQuery.setParameter("statusUserParam", 1); // active
//			theQuery.setParameter("idParam", user.getId());
//			theQuery.executeUpdate();
//			    } return true;
//			     	
//		} catch (HibernateException e) {
//			throw new DaoException("Hibernate getting error from method registration", e);
//		}
//	}
	
//    @Override	
//	public String getRole(String login, String password) throws DaoException {
//		String role="";
//		
//		try {
////		Session currentSession = sessionFactory.getCurrentSession();
////		Query query = currentSession.createQuery("from User where login=:loginParam and password=:passwordParam", User.class);
////		query.setParameter("loginParam", "login");
////		query.setParameter("passwordParam", "password");
//		if (logination(login, password)==true) {
//				role =getUser(login).getRole().getRoleName();
//			}
//				
//		} catch (HibernateException e) {
//			throw new DaoException("Hibernate getting error from method getRole", e);
//		}
//		
//		return role;
//	}


	

	}

