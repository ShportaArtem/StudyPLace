package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Certificate;

public class CertificateRep {
	private static final String SQL_FIND_CERTIFICATE_BY_ID = "SELECT * FROM certificates WHERE id=?";

	public Certificate findCertificateById(Connection con, int id) throws SQLException {
		Certificate user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_CERTIFICATE_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractCertificate(rs);
			}
		return user;
	}
	
	private Certificate extractCertificate(ResultSet rs) throws SQLException {
		Certificate user = new Certificate();
		user.setId(rs.getInt("ID"));
		user.setCourseId(rs.getInt("CourseID"));
		user.setUserId(rs.getInt("UserID"));
		user.setMark(rs.getDouble("Mark"));
		return user;
	}
}
