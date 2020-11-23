package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Comment;


public class CommentRep {
	
	private static final String SQL_CREATE_COMMENTS_COURSE = "INSERT INTO comments VALUES (DEFAULT, now(), ?, ?, ?,null)";
	private static final String SQL_FIND_ALL_COMMENTS_BY_COURSE_ID = "SELECT * FROM comments WHERE courseid=?";
	private static final String SQL_FIND_ALL_COMMENTS = "SELECT * FROM comments";
	//private static final String SQL_DELETE_COMMENT_BY_USER_ID = "DELETE FROM comments WHERE id=?";
	/**
	 * Returns all course.
	 * @param <Courses>
	 * 
	 * @return List of course models. 
	 */
	
	public  List<Comment> findAllComments(Connection con) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_COMMENTS);

			while (rs.next()) {
				comments.add(extractComments(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return comments;
	}
	
	private  Comment extractComments(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("ID"));
		comment.setDateTime(rs.getDate("DateTime"));
		comment.setUserId(rs.getInt("UserID"));
		comment.setText(rs.getString("Text"));
		comment.setCourseID(rs.getInt("CourseID"));
		comment.setPublicationID(rs.getInt("PublicationID"));
		return comment;
	}
	
	public List<Comment> findCommentsByCourseId(Connection con, int id) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_ALL_COMMENTS_BY_COURSE_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comments.add(extractComments(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return comments;
	}
	
	public Comment insertCommentCourse(Connection con, Comment comment) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_COMMENTS_COURSE, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setInt(k++, comment.getUserId());
			pstmt.setString(k++, comment.getText());
			pstmt.setInt(k++, comment.getCourseID());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int commentId = rs.getInt(1);
					comment.setId(commentId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return comment;
	}
	
}
	
	
	