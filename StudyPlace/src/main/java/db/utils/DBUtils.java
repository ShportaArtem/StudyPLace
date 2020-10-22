package db.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Util class for DB.
 * @author A.Shporta
 *
 */
public class DBUtils {
	
	private static Logger LOG = Logger.getLogger(DBUtils.class);
	
	
	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				LOG.error("Cannot rollback transaction", e);
			}
		}
	}
	/**
	 * Closes a AutoCloseable object.
	 */
	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch(Exception e) {
				LOG.error("Cannot close "+closeable.getClass(), e);
			}
		}
	}
	

}
