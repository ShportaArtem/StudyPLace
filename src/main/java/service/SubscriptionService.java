package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.SubscriptionRep;
import db.utils.DBUtils;
import model.Subscription;

public class SubscriptionService {
	private static final Logger LOG = Logger.getLogger(SubscriptionService.class);
	
	private DBManager dbManager;
	private SubscriptionRep subsRep;
	
	public SubscriptionService(DBManager dbManager, SubscriptionRep subsRep)  {
		this.dbManager = dbManager;
		this.subsRep= subsRep;
	}
	
	public Subscription insertSubs(Subscription subs) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			subs = subsRep.insertSubs(con, subs);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_SUBSCRIPTION, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_SUBSCRIPTION, ex);
		} finally {
			DBUtils.close(con);
		}
		return subs;
	}
	
	public List<Subscription> findSubByCourseId(int id) throws AppException {
		List<Subscription> subs = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			subs = subsRep.findSubByCourseId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			DBUtils.close(con);
		}
		return subs;
	}
	
	public List<Subscription> findSubByUserId(int id) throws AppException {
		List<Subscription> subs = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			subs = subsRep.findSubByUserId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			DBUtils.close(con);
		}
		return subs;
	}
	
	
}
