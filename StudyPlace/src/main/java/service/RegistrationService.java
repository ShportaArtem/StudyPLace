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

public class RegistrationService {
	private static final Logger LOG = Logger.getLogger(RegistrationService.class);
	
	private DBManager dbManager;
	private UserRep userRep;
	
	public RegistrationService(DBManager dbManager, UserRep userRep)  {
		this.dbManager = dbManager;
		this.userRep= userRep;
	}
	
	public void insertUser(User user) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			userRep.insertUser(con, user);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
}
