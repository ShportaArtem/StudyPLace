package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.AnswerForQuestion;
import model.UserAnswer;

public class UserAnswerRep {
	
	private static final String SQL_CREATE_USERSANSWER = "INSERT INTO usersanswers VALUES (DEFAULT, ?, ?)";
	private static final String SQL_FIND_ALL_USERSANSWER ="SELECT * FROM usersanswers";
	private static final String SQL_FIND_USERSANSWERS_BY_USER_ID="SELECT usersanswers.* FROM usersanswers, answersforquestions, questions WHERE usersanswers.UserID=? AND questions.TaskID=? AND usersanswers.AnswerForQuestionID=answersforquestions.ID AND answersforquestions.QuestionID=questions.ID";
	
	public List<UserAnswer> findAllUserAnswers(Connection con) throws SQLException {
		List<UserAnswer> answers = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERSANSWER);

			while (rs.next()) {
				answers.add(extractUserAnswer(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return answers;
	}
	
	public static List<UserAnswer> findUserAnswerByUserIdTaskId(Connection con, int userId, int taskId) throws SQLException {
		List<UserAnswer> answers = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(SQL_FIND_USERSANSWERS_BY_USER_ID);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, taskId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				answers.add(extractUserAnswer(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return answers;
	}
	
	public UserAnswer insertUserAnswer(Connection con, UserAnswer userAnswer) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_USERSANSWER, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setInt(k++, userAnswer.getUserId());
			pstmt.setInt(k++, userAnswer.getAnswerForQuestionId());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					userAnswer.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return userAnswer;
	}
	
	private static UserAnswer extractUserAnswer(ResultSet rs) throws SQLException {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setId(rs.getInt("ID"));
		userAnswer.setUserId(rs.getInt("UserID"));
		userAnswer.setAnswerForQuestionId(rs.getInt("AnswerForQuestionID"));
		
		return userAnswer;
	}
}
