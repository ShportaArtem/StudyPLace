package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Publication;


public class PublicationRep {
	
	private static final String SQL_CREATE_PUBLICATION = "INSERT INTO publications VALUES (DEFAULT, ?, ?, ?, ?, now())";
	private static final String SQL_FIND_ALL_PUBLICATIONS_BY_COURSE_ID = "SELECT * FROM publications WHERE courseid=?";
	private static final String SQL_FIND_ALL_PUBLICATIONS = "SELECT * FROM publications";
	private static final String SQL_FIND_PUBLICATION_BY_POSITION = "SELECT * FROM publications WHERE position=?";
	//private static final String SQL_DELETE_COMMENT_BY_USER_ID = "DELETE FROM comments WHERE id=?";
	/**
	 * Returns all course.
	 * @param <Publications>
	 * 
	 * @return List of course models. 
	 */
	
	public  List<Publication> findAllPublications(Connection con) throws SQLException {
		List<Publication> publications = new ArrayList<Publication>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PUBLICATIONS);

			while (rs.next()) {
				publications.add(extractPublications(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return publications;
	}
	
	private  Publication extractPublications(ResultSet rs) throws SQLException {
		Publication publication = new Publication();
		publication.setId(rs.getInt("ID"));
		publication.setName(rs.getString("name"));
		publication.setDescription(rs.getString("description"));
		publication.setCourseID(rs.getInt("CourseID"));
		publication.setPosition(rs.getInt("Position"));
		publication.setDateTime(rs.getDate("DateTime"));
		return publication;
	}
	
	public List<Publication> findPublicationsByCourseId(Connection con, int id) throws SQLException {
		List<Publication> publications = new ArrayList<Publication>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_ALL_PUBLICATIONS_BY_COURSE_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				publications.add(extractPublications(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return publications;
	}
	
	public Publication insertPublication(Connection con, Publication publication) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_PUBLICATION, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, publication.getName());
			pstmt.setString(k++, publication.getDescription());
			pstmt.setInt(k++, publication.getCourseID());
			pstmt.setInt(k++, publication.getPosition());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int publicationtId = rs.getInt(1);
					publication.setId(publicationtId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return publication;
	}
	
	public Publication findPublicationByPosition(Connection con, int id) throws SQLException {
		Publication publication = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_PUBLICATION_BY_POSITION);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				publication = extractPublications(rs);
			}
		return publication;
	}
	
}
	
	
	