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
import model.Certificate;
import model.Course;

public class CertificateRep {
	private static final String SQL_FIND_CERTIFICATE_BY_ID = "SELECT * FROM certificates WHERE id=?";
	private static final String SQL_FIND_CERTIFICATE_BY_USERCOURSE = "SELECT * FROM certificates WHERE UserID=? AND CourseID=?";
	private static final String SQL_INSERT_CERTIFICATE = "INSERT INTO certificates VALUES(DEFAULT, ?, ?, ?)";
	private static final String SQL_FIND_CERTIFICATES_BY_USER = "SELECT * FROM certificates WHERE UserID=?";
	
	public Certificate insertCertificate(Connection con, Certificate cert) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_INSERT_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setInt(k++, cert.getUserId());
			pstmt.setInt(k++, cert.getCourseId());
			pstmt.setDouble(k++, cert.getMark());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int certId = rs.getInt(1);
					cert.setId(certId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return cert;
	}
	
	public Certificate findCertificateById(Connection con, int id) throws SQLException {
		Certificate cert = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_CERTIFICATE_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cert = extractCertificate(rs);
			}
		return cert;
	}
	
	private Certificate extractCertificate(ResultSet rs) throws SQLException {
		Certificate user = new Certificate();
		user.setId(rs.getInt("ID"));
		user.setCourseId(rs.getInt("CourseID"));
		user.setUserId(rs.getInt("UserID"));
		user.setMark(rs.getDouble("Mark"));
		return user;
	}
	
	public Certificate findCertificateByUserCourseId(Connection con, int userId, int courseId) throws SQLException
	{
		Certificate cert = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_CERTIFICATE_BY_USERCOURSE);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, courseId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cert = extractCertificate(rs);
			}
		return cert;
	}
	
	public List<Certificate> findCertificatesByUserID(Connection con, int userId) throws SQLException
	{
		List<Certificate> certs = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SQL_FIND_CERTIFICATES_BY_USER);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				certs.add(extractCertificate(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		
		return certs;
	}
}
