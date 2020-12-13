package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Course;

public class CourseRep {
	
	private static final String SQL_CREATE_COURSE = "INSERT INTO Courses VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";	
	private static final String SQL_FIND_ALL_COURSES="SELECT * FROM courses";
	private static final String SQL_FIND_COURSES_BY_ID = "SELECT * FROM courses WHERE id=?";
	private static final String SQL_SEARCH_IN_COURSES = "select * from courses where name like ? "; //or description like '%?%'
	private static final String SQL_FIND_COURSES_BY_USER_ID = "select * from courses where TeacherID = ? ";
	private static final String SQL_UPDATE_COURSE_BY_ID = "UPDATE courses SET name=?, Description =?, Price =?, Picture =? WHERE ID=?";

	
	public Course insertCourse(Connection con, Course course) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_COURSE, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, course.getName());
			pstmt.setString(k++, course.getDescription());
			pstmt.setString(k++, course.getPassword());
			pstmt.setInt(k++, course.getTeacherId());
			pstmt.setString(k++, course.getPicture());
			if(course.getPrice()==null) {
				pstmt.setNull(k++, Types.DOUBLE);
			}else {
				pstmt.setDouble(k++, course.getPrice());
			}
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int courseId = rs.getInt(1);
					course.setId(courseId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return course;
	}
	
	
	public  List<Course> findAllCourses(Connection con) throws SQLException {
		List<Course> courses = new ArrayList<Course>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_COURSES);

			while (rs.next()) {
				courses.add(extractCourse(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return courses;
	}
	
	private  Course extractCourse(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt("ID"));
		course.setName(rs.getString("Name"));
		course.setDescription(rs.getString("Description"));
		course.setPassword(rs.getString("Password"));
		course.setTeacherId(rs.getInt("TeacherID"));
		course.setPicture(rs.getString("Picture"));
		course.setPrice(rs.getDouble("Price"));
		return course;
	}
	
	public Course findCourseById(Connection con, int id) throws SQLException {
		Course course = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_COURSES_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				course = extractCourse(rs);
			}
		return course;
	}
	
	public List<Course> searchInCourses(Connection con, String str) throws SQLException {
		List<Course> courses = new ArrayList<Course>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_SEARCH_IN_COURSES);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courses.add(extractCourse(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return courses;
	}
	
	public List<Course> findCoursesByTeacherId(Connection con, Integer id) throws SQLException {
		List<Course> courses = new ArrayList<Course>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_COURSES_BY_USER_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courses.add(extractCourse(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return courses;
	}
	
	public boolean updateCourseById(Connection con, Course course) throws SQLException { 
		boolean res = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_UPDATE_COURSE_BY_ID, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, course.getName());
			pstmt.setString(k++, course.getDescription());
			if(course.getPrice()==null) {
				pstmt.setNull(k++, Types.DOUBLE);
			}else {
				pstmt.setDouble(k++, course.getPrice());
			}
			pstmt.setString(k++, course.getPicture());
			pstmt.setInt(k++, course.getId());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					res = true;
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return res;
	}
}
