package service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CertificateRep;
import db.utils.DBUtils;
import model.Certificate;

public class CertificateService {
	
	private static final Logger LOG = Logger.getLogger(CertificateService.class);

	private DBManager dbManager;
	private CertificateRep certificateRep;
	
	public CertificateService(DBManager dbManager, CertificateRep certificateRep) {
		super();
		this.dbManager = dbManager;
		this.certificateRep = certificateRep;
	}
	
	public Certificate findCertificateById(int id) throws AppException {
		Certificate user = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			user = certificateRep.findCertificateById(con, id);
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
