package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.utils.DBUtils;
import model.UserInfo;

public class UserInfoRep {
	
	private static final String SQL_FIND_USER_INFO_BY_ID = "SELECT * FROM Users_info WHERE ID=?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE Users_info SET about=?, `E-Mail`=?, messanger=?, `Web-site`=? WHERE ID=?";
	private static final String SQL_CREATE_USER_INFO = "INSERT INTO Users_info VALUES (DEFAULT, null, 1, ?, ?, ?, ?)";

	public UserInfo findUserInfoById(Connection con, int id) throws SQLException {
		UserInfo userInfo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_USER_INFO_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userInfo = extractUserInfo(rs);
			}
		return userInfo;
	}
	
	
	
	private UserInfo extractUserInfo(ResultSet rs) throws SQLException {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(rs.getInt("ID"));
		userInfo.setAbout(rs.getString("About"));
		userInfo.setLocked(rs.getBoolean("IsLocked"));
		userInfo.setEmail(rs.getString("E-Mail"));
		userInfo.setMessanger(rs.getString("Messanger"));
		userInfo.setWebsite(rs.getString("Web-site"));
		return userInfo;
	}
	
	public boolean updateUserInfoById(Connection con, UserInfo userInfo) throws SQLException { 
		boolean res = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, userInfo.getAbout());
			pstmt.setString(k++, userInfo.getEmail());
			pstmt.setString(k++, userInfo.getMessanger());
			pstmt.setString(k++, userInfo.getWebsite());
			pstmt.setInt(k++, userInfo.getId());
			
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



	public UserInfo insertUserInfo(Connection con, UserInfo userInfo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_USER_INFO, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, userInfo.getWebsite());
			pstmt.setString(k++, userInfo.getMessanger());
			pstmt.setString(k++, userInfo.getEmail());
			pstmt.setString(k++, userInfo.getAbout());
			
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					userInfo.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return userInfo;
		
	}

}
