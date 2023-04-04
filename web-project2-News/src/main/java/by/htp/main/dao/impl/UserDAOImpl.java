package by.htp.main.dao.impl;


import java.util.Date;
import by.htp.main.bean.User;
import by.htp.main.dao.DaoException;
import by.htp.main.dao.UserDAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.*;
import org.springframework.beans.factory.annotation.Autowired;

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
 	public boolean logination(String login, String password) throws DaoException {
    	
    	try {
			Session currentSession = sessionFactory.getCurrentSession();
			if (loginExist(login)==true) {
			Query theQuery = currentSession
					.createQuery("from User where password=:passwordParam,", User.class);

			theQuery.setParameter("passwordParam", hashPassword(password));
			} return true;
				
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method logination", e);
		}
    	
//		Connection con = null;
//		PreparedStatement ps=null;
//		ResultSet rs = null;
//		boolean success = false;
//		
//		try {	
//			con = pool.takeConnection();     
//			ps = con.prepareStatement(SELECT_PASSWORD_FROM_LOGIN);
//			ps.setString(1, login);
//			
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				if (checkPassword(password, rs.getString("password"))){
////		        if (rs.getString("password").equals(password)) {	        	
//		        	success = true;
//		        }
//			}
//			
//			if (success == true) {
//				return true;
//			}		
//		}
//    	catch (SQLException e) {
//			throw new DaoException("error select login in news.users from method logination", e);
//		}
//		catch (ConnectionPoolException e) {
//			throw new DaoException("problem with connection pool", e);		
//        } 
//		finally {
//			pool.closeConnection(con, ps, rs);
//	   }
//		
//		return false;
    }
     
	@Override
	public boolean loginExist(String login) throws DaoException {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			User user = currentSession.get(User.class, login);
			return true;
			
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method loginExist", e);
		}
		
//		Connection con = null;
//		PreparedStatement ps=null;
//		ResultSet rs = null;		
//		try {
//			con = pool.takeConnection();     
//			ps = con.prepareStatement(SELECT_PASSWORD_FROM_LOGIN);
//			ps.setString(1, login);
//			
//			rs = ps.executeQuery();
//			
//			boolean success = false;
//			
//			if (rs.next()) {
//				success = true;
//			}
//					
//			if (success == true) {
//				return true;
//			}					
//		}
//		catch (SQLException e) {
//			throw new DaoException("error select login in news.users from method loginExist", e);
//		}
//		catch (ConnectionPoolException e) {
//			throw new DaoException("problem with connection pool", e);
//		}
//		finally {
//			pool.closeConnection(con, ps, rs);
//		}
//		
//		return false;		
	}    
    
     	
	public String getRole(String login, String password) throws DaoException {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			User user = currentSession.get(User.class, login);
			
			return user.getRole().getRoleName();
			
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method getRole", e);
		}
	
//		String nameRole = "guest";
//		Connection con = null;
//		PreparedStatement ps=null;
//		ResultSet rs = null;
//		
//		try {
//			if (logination(login, password)==true) {			
//				con = pool.takeConnection();     
//				ps = con.prepareStatement(SELECT_ROLE_NAME_FROM_LOGIN);
//				ps.setString(1, login);
//				
//				rs = ps.executeQuery();					
//				
//				if (rs.next()) {
//					nameRole = rs.getString("role_name");
//				}
//			
//				if (nameRole != null) {
//					return nameRole;
//				}			
//			}		
//		}
//		catch (SQLException e) {
//			throw new DaoException("error select role_name in tables users, role from method getRole", e);
//		}
//		catch (ConnectionPoolException e) {
//			throw new DaoException("problem with connection pool", e);
//		} 
//		finally {
//			pool.closeConnection(con, ps, rs);
//		}
//	return nameRole;
}
	
//	public boolean isPermission(String login, String password) throws DaoException {
//		Connection con = null;
//		PreparedStatement ps=null;
//		ResultSet rs = null;
//		
//		try{
//			if (logination(login, password)==true) {						
//				con = pool.takeConnection();     
//				ps = con.prepareStatement(SELECT_PERMISSION_NAME_FROM_PREMID_LOGIN);
//				ps.setInt(1, 2); // permission editing
//				ps.setString(2, login);
//				
//				rs = ps.executeQuery();					
//				
//				boolean success = false;
//				
//				if (rs.next()) {
//					success = true;
//				}
//					
//				if (success == true) {
//					return true;
//				}
//			}
//		}
//		catch (SQLException e) {
//			throw new DaoException("error select permission_name in tables users, role, permission from method isAdmin", e);
//		}
//		catch (ConnectionPoolException e) {
//			throw new DaoException("problem with connection pool", e);
//		} 
//		finally {
//			pool.closeConnection(con, ps, rs);
//		}
//	return false;			
//	}	

	@Override
	public boolean registration(User user) throws DaoException {
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			    if (logination (user.getLogin(), user.getPassword())==false) {
			currentSession.save(user);
			Query theQuery = currentSession
					.createQuery("update User set password=:passwordParam, role=:roleParam, dateRegistration=:dateParam, statusUser=:statusUserParam  where id=:idParam", User.class);

			theQuery.setParameter("passwordParam", hashPassword(user.getPassword())); 
			theQuery.setParameter("roleParam", 2); // user
			Date now = new Date();
			theQuery.setParameter("dateParam", now.getTime());
			theQuery.setParameter("statusUserParam", 1); // active
			theQuery.setParameter("idParam", user.getId());
			theQuery.executeUpdate();
			    } return true;
			     	
		} catch (HibernateException e) {
			throw new DaoException("Hibernate getting error from method registration", e);
		}
	}
		
//		PreparedStatement ps=null;
//				
//		if (loginExist(user.getLogin())==false) {
//			locker.lock();
//			try (Connection con = pool.takeConnection()) {	
//				con.setAutoCommit(false);
//				ps = con.prepareStatement(INSERT_USERS);
//	
//				ps.setString(1, user.getLogin());
//				ps.setString(2, hashPassword(user.getPassword()));
//				Date now = new Date();
//				java.sql.Date date = new java.sql.Date(now.getTime());
//				ps.setDate(3, date);
//				ps.setString(4, "2");
//				ps.setString(5, "1");
//	
//				boolean success = false;
//				int user_id=-1;
//				
//				con.commit();
//				
//				if (ps.executeUpdate()>=1) {	
//					try {
//						PreparedStatement ps2 = con.prepareStatement(SELECT_ID_FROM_LOGIN);
//						ps2.setString(1, user.getLogin());					
//						ResultSet rs = ps2.executeQuery();
//						
//						con.commit();
//						
//						while (rs.next()) {
//							user_id = rs.getInt("id");
//							success = true;	
//						}
//					}
//					catch (SQLException e) {
//						con.rollback();
//						throw new DaoException("error select id in tables users from method registration",e);
//					}
//				}
//				
//				if (success = true) {
//					success = false;
//					try {
//						PreparedStatement ps3 = con.prepareStatement(INSERT_USERS_DETAILS);
//						ps3.setInt(1, user_id);
//						ps3.setString(2, user.getUserName());
//						ps3.setString(3, user.getUserSurname());
//						ps3.setString(4, user.getBirthday());
//						ps3.setString(5, user.getEmail());						
//						
//						if (ps3.executeUpdate()>=1) {
//							con.commit();
//							success = true;	
//						}
//					} 
//					catch (SQLException e) {
//						con.rollback();
//						throw new DaoException("error insert user in table user_details from method registration",e);
//					}					
//				} 
//				
//				if (success == true) {
//					return true;
//				}
//								
//			}
//			catch (SQLException e) {
//					throw new DaoException("error insert user in tables users, user_details from method registration",e);
//			}
//			catch (ConnectionPoolException e) {
//				throw new DaoException("problem with connection pool", e);
//			}
//			finally{
//				locker.unlock(); 
//			}
//		}
//		return false;
	}

