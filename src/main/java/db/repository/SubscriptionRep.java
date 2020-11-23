package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Subscription;

public class SubscriptionRep {
	
	private static final String SQL_CREATE_SUBSCRIPTION = "INSERT INTO subscriptions VALUES (?, ?)";	
	//private static final String SQL_COUNT_FOLLOWERS = "SELECT COUNT(*) FROM subscriptions WHERE courseid=?";
	private static final String SQL_FIND_SUBSCRIPTION_BY_COURSE_ID = "SELECT * FROM subscriptions WHERE courseid=?";
	//private static final String SQL_FIND_SUBSCRIPTION_BY_USER_ID = "SELECT * FROM subscriptions WHERE userid=? and courseid=";
	
	public Subscription insertSubs(Connection con, Subscription subs) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_SUBSCRIPTION, Statement.RETURN_GENERATED_KEYS);
			
			int k = 1;
			pstmt.setInt(k++, subs.getUserId());
			pstmt.setInt(k++, subs.getCourseId());
			System.out.print("метод работает");
			pstmt.executeUpdate();
				
		} finally {
			DBUtils.close(rs); 
			DBUtils.close(pstmt);
		}

		return subs;
	}
	
	private  Subscription extractSubscription(ResultSet rs) throws SQLException {
		Subscription subs = new Subscription();
		subs.setUserId(rs.getInt("UserID"));
		subs.setCourseId(rs.getInt("CourseID"));
		return subs;
	}
	
	public List<Subscription> findSubByCourseId(Connection con, int id) throws SQLException {
		List<Subscription> subs = new ArrayList<Subscription>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_SUBSCRIPTION_BY_COURSE_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				subs.add(extractSubscription(rs));
			}
			con.commit();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return subs;
	}
	
	
	
}

