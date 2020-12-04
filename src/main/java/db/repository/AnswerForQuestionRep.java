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

public class AnswerForQuestionRep {
	
	private static final String SQL_CREATE_ANSWER_FOR_QUESION = "INSERT INTO answersforquestions VALUES (DEFAULT, ?, ?, ?)";
	private static final String SQL_FIND_ALL_ANSWERS="SELECT * FROM answersforquestions";
	
	public List<AnswerForQuestion> findAllAnswers(Connection con) throws SQLException {
		List<AnswerForQuestion> answers = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ANSWERS);

			while (rs.next()) {
				answers.add(extractAnswer(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(stmt);
		}
		return answers;
	}
	
	public AnswerForQuestion insertAnswerForQuestion(Connection con, AnswerForQuestion answer) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_ANSWER_FOR_QUESION, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setInt(k++, answer.getQuestionId());
			pstmt.setString(k++, answer.getValue());
			pstmt.setBoolean(k++, answer.isCorrect());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					answer.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return answer;
	}
	
	private AnswerForQuestion extractAnswer(ResultSet rs) throws SQLException {
		AnswerForQuestion answer = new AnswerForQuestion();
		answer.setId(rs.getInt("ID"));
		answer.setQuestionId(rs.getInt("QuestionID"));
		answer.setValue(rs.getString("Value"));
		answer.setCorrect(rs.getBoolean("IsCorrect"));
		
		return answer;
	}
}
