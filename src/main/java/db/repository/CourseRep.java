package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import db.utils.DBUtils;
import model.Course;

public class CourseRep {
	
	private static final String SQL_CREATE_COURSE = "INSERT INTO Courses VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";	
	
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
			pstmt.setBlob(k++, course.getPicture());
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
}
