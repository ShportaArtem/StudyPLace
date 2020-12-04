package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Question;

public class QuestionRep {
	
	private static final String SQL_CREATE_QUESTION= "INSERT INTO questions VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ALL_QUESTIONS_BY_TASK_ID="SELECT * FROM questions WHERE TaskID=?";
	
	public Question insertQuestion(Connection con, Question question) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setInt(k++, question.getTaskId());
			pstmt.setString(k++, question.getQuestion());
			pstmt.setString(k++, question.getDescription());
			pstmt.setInt(k++, question.getMark());
			pstmt.setInt(k++, question.getPosition());
			pstmt.setBoolean(k++, question.isOneCorrectAnswer());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					question.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return question;
	}
	
	public List<Question> findAllQuestionsByTaskId(Connection con, Integer id) throws SQLException{
		List<Question> questions = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_FIND_ALL_QUESTIONS_BY_TASK_ID, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questions.add(extractQuestion(rs));
			}
			}finally {
				DBUtils.close(rs);
				DBUtils.close(pstmt);
			}
		return questions;
	}
	
	private Question extractQuestion(ResultSet rs) throws SQLException {
		Question question = new Question();
		question.setId(rs.getInt("ID"));
		question.setTaskId(rs.getInt("TaskID"));
		question.setQuestion(rs.getString("Question"));
		question.setDescription(rs.getString("Description"));
		question.setMark(rs.getInt("Mark"));
		question.setPosition(rs.getInt("Position"));
		question.setOneCorrectAnswer(rs.getBoolean("OneCorrectAnswer"));
		return question;
	}
}
