package service;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.UserRep;
import db.utils.DBUtils;
import model.User;

/**
 * Login service. Works with DBManager and repositories. 
 * 
 * @author A.Shporta
 * 
 */
public class LoginService {

	private static final Logger LOG = Logger.getLogger(LoginService.class);

	private DBManager dbManager;
	private UserRep userRep;
	
	public LoginService(DBManager dbManager, UserRep userRep)  {
		this.dbManager = dbManager;
		this.userRep= userRep;
	}

	/**
	 * find user by login
	 * 
	 * @param login
	 * 		Login user that will be find
	 * @return user model
	 * @throws AppException
	 */
	public User findUserByLogin(String login) throws AppException {
		User user = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			user = userRep.findUserByLogin(con, login);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			DBUtils.close(con);
		}
		return user;
	}
	
	/**
	 * find user by id
	 * 
	 * @param id
	 * 		Id user that will be find
	 * @return user model
	 * @throws AppException
	 */
	public User findUserById(int id) throws AppException {
		User user = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			user = userRep.findUserById(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return user;
	}

}
