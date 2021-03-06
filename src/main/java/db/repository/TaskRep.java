package db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.utils.DBUtils;
import model.Task;
import model.UserAnswer;

public class TaskRep {
	private static final String SQL_FIND_TASKS_BY_PUBLICATION_ID = "SELECT * FROM tasks WHERE publications_ID=?";
	private static final String SQL_CREATE_TASK = "INSERT INTO tasks VALUES (DEFAULT, ?, ?, ?)";
	private static final String SQL_GET_TASKS_BY_COURSE_ID = "SELECT tasks.* FROM tasks, publications WHERE publications.ID = tasks.publications_ID AND publications.CourseID=?";
	
	public Task findTaskByPublicationId(Connection con, int id) throws SQLException {
		Task task = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			pstmt = con.prepareStatement(SQL_FIND_TASKS_BY_PUBLICATION_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				task = extractTask(rs);
			}
		return task;
	}
	
	public Task insertUser(Connection con, Task task) throws SQLException { 

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(SQL_CREATE_TASK, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, task.getName());
			pstmt.setString(k++, task.getDescription());
			pstmt.setInt(k++, task.getPublicationId());
			
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int userId = rs.getInt(1);
					task.setId(userId);
				}
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}

		return task;
	}
	
	public List<Task> getTasksByCourseId(Connection con, int id) throws SQLException
	{
		List<Task> tasks = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(SQL_GET_TASKS_BY_COURSE_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				tasks.add(extractTask(rs));
			}
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return tasks;
	}
	
	private Task extractTask(ResultSet rs) throws SQLException {
		Task task = new Task();
		task.setId(rs.getInt("ID"));
		task.setDescription(rs.getString("Description"));
		task.setName(rs.getString("Name"));
		task.setPublicationId(rs.getInt("publications_ID"));
		
		return task;
	}
}
