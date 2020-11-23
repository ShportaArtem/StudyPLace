package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	
	public List<Course> findCourses() throws AppException{
		List<Course> courses = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courses = courseRep.findAllCourses(con);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			DBUtils.close(con);
		}
		return courses;
	}
	
	public Course findCourseById(int id) throws AppException {
		Course course = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			course = courseRep.findCourseById(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return course;
	}
	
	public List<Course> searchInCourses(String str) throws AppException{
		List<Course> courses = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courses = courseRep.searchInCourses(con, str);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
		} finally {
			DBUtils.close(con);
		}
		return courses;
	}
	
	public void updateCourse(Course course) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courseRep.updateCourseById(con, course);
			con.commit();
		} catch (SQLException ex ) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
}
