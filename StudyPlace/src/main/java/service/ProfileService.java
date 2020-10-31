package service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.UserInfoRep;
import db.repository.UserRep;
import db.utils.DBUtils;
import model.User;
import model.UserInfo;

public class ProfileService {
	
	private static final Logger LOG = Logger.getLogger(LoginService.class);

	private DBManager dbManager;
	private UserInfoRep userInfoRep;
	private UserRep userRep;
	
	public ProfileService(DBManager dbManager, UserInfoRep userInfoRep, UserRep userRep)  {
		this.dbManager = dbManager;
		this.userInfoRep= userInfoRep;
		this.userRep= userRep;
	}
	
	public UserInfo findUserInfoById(int id) throws AppException {
		UserInfo userInfo = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			userInfo = userInfoRep.findUserInfoById(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return userInfo;
	}
	
	public void updateUser(User user, int userInfoId) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			userRep.updateUserByIdWithUserInfo(con, user, userInfoId);
			con.commit();
		} catch (SQLException ex ) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
	
	public void updateUser(User user) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			userRep.updateUserById(con, user);
			con.commit();
		} catch (SQLException ex ) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
	

	
	public void updateUserInfo(UserInfo userInfo) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			userInfoRep.updateUserInfoById(con, userInfo);
			con.commit();
		} catch (SQLException ex ) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
	
	public UserInfo insertUserInfo(UserInfo userInfo) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			userInfo = userInfoRep.insertUserInfo(con, userInfo);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return userInfo;
	}

	
}
