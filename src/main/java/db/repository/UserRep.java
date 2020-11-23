package db.repository;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.User;


public class UserRep {
	
	private static final String SQL_CREATE_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ALL_USERS="SELECT * FROM users";
	private static final String SQL_FIND_ALL_USERS_BY_ROLE_ID="SELECT * FROM users WHERE role_id=?";
	private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE username=?";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET username=?,  name=?, sername=? WHERE ID=?";
	private static final String SQL_UPDATE_USER_BY_ID_WITH_ID = "UPDATE users SET username=?,  name=?, sername=?, InfoID=? WHERE ID=?";

	/**
	 * Returns all users.
	 * 
	 * @return List of user models.
	 */
	public List<User> findAllUsers(Connection con) throws SQLException {
		List<User> users = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);

			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return users;
	}
	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws SQLException
	 */
	public boolean updateUserById(Connection con, User user) throws SQLException { 
		boolean res = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER_BY_ID, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getSurname());
			pstmt.setInt(k++, user.getId());
			
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
	
	public boolean updateUserByIdWithUserInfo(Connection con, User user, int userInfoId) throws SQLException { 
		boolean res = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER_BY_ID_WITH_ID, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getSurname());
			pstmt.setInt(k++, userInfoId);
			pstmt.setInt(k++, user.getId());
			
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
	
	public List<User> findAllUsersByRoleId(Connection con, Integer roleId) throws SQLException, NoSuchAlgorithmException {
		List<User> users = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_ALL_USERS_BY_ROLE_ID, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, roleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			}finally {
				DBUtils.close(rs);
				DBUtils.close(pstmt);
			}
		return users;
	}
	/**
	 * Returns user by id.
	 * 
	 * @return User model.
	 */
	public User findUserById(Connection con, int id) throws SQLException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		return user;
	}
	/**
	 * Returns user by login.
	 * 
	 * @return User model.
	 */
	public User findUserByLogin(Connection con,String login) throws SQLException{
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		return user;
	}
	/**
	 * Extracts a user model from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user model will be extracted.
	 * @return User model
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setRoleId(rs.getInt("RoleID"));
		user.setInfoId(rs.getInt("InfoID"));
		user.setLogin(rs.getString("username"));
		user.setPassword(rs.getString("Password"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("sername"));
		return user;
	}
	
	/**
	 * Insert a user to db.
	 * 
	 * @param user
	 *            The user that will be insert.
	 */
	public User insertUser(Connection con, User user) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getSurname());
			pstmt.setNull(k++, Types.INTEGER);
			pstmt.setInt(k++, user.getRoleId());
			pstmt.setString(k++, user.getPassword());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					user.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return user;
	}

	
	/**
	 * Delete a user from db.
	 * 
	 * @param user id
	 *            The user id that will be delete.
	 */
	public boolean deleteUser(Connection con, int userId) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(SQL_DELETE_USER);

			int k = 1;
			pstmt.setInt(k++, userId);

			return pstmt.executeUpdate() > 0;
		} finally {
			DBUtils.close(pstmt);
		}
	}
}
