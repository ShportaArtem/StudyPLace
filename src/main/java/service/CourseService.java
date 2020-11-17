package service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CourseRep;
import db.utils.DBUtils;
import model.Course;

public class CourseService {
	private static final Logger LOG = Logger.getLogger(CourseService.class);
	
	private DBManager dbManager;
	private CourseRep courseRep;
	
	public CourseService(DBManager dbManager, CourseRep courseRep)  {
		this.dbManager = dbManager;
		this.courseRep= courseRep;
	}
	
	public Course insertCourse(Course course) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			course = courseRep.insertCourse(con, course);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return course;
	}
}
