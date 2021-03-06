package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.AnswerForQuestionRep;
import db.repository.CourseRep;
import db.repository.PublicationRep;
import db.repository.QuestionRep;
import db.repository.TaskRep;
import db.repository.UserAnswerRep;
import db.utils.DBUtils;
import model.AnswerForQuestion;
import model.Course;
import model.Publication;
import model.Question;
import model.Task;
import model.User;
import model.UserAnswer;

public class CourseService {
	private static final Logger LOG = Logger.getLogger(CourseService.class);
	
	private DBManager dbManager;
	private CourseRep courseRep;
	private TaskRep taskRep;
	private QuestionRep questionRep;
	private AnswerForQuestionRep answerForQuestionRep;
	private PublicationRep publicationRep;
	private UserAnswerRep userAnswerRep;
	
	public CourseService(DBManager dbManager, CourseRep courseRep, TaskRep taskRep, QuestionRep questionRep,
			AnswerForQuestionRep answerForQuestionRep, PublicationRep publicationRep, UserAnswerRep userAnswerRep) {
		super();
		this.dbManager = dbManager;
		this.courseRep = courseRep;
		this.taskRep = taskRep;
		this.questionRep = questionRep;
		this.answerForQuestionRep = answerForQuestionRep;
		this.publicationRep = publicationRep;
		this.userAnswerRep = userAnswerRep;
	}

	public Course insertCourse(Course course) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			course = courseRep.insertCourse(con, course);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return course;
	}
	
	public Task insertTask(Task task) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			task = taskRep.insertUser(con, task);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_TASK, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_TASK, ex);
		} finally {
			DBUtils.close(con);
		}
		return task;
	}
	
	public Question insertQuestion(Question question) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			question = questionRep.insertQuestion(con, question);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_QUESTION, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_QUESTION, ex);
		} finally {
			DBUtils.close(con);
		}
		return question;
	}
	
	public UserAnswer insertUserAnswer(UserAnswer userAnswer) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			userAnswer = userAnswerRep.insertUserAnswer(con, userAnswer);
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_INSERT_QUESTION, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_QUESTION, ex);
		} finally {
			DBUtils.close(con);
		}
		return userAnswer;
	}
	
	public List<Question> findQuestionsByTaskId(int id) throws AppException{
		List<Question> questions = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			questions = questionRep.findAllQuestionsByTaskId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_QUESTIONS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_QUESTIONS, ex);
		} finally {
			DBUtils.close(con);
		}
		return questions;
	}
	
	public AnswerForQuestion insertAnswerForQuestion(AnswerForQuestion answer) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			answer = answerForQuestionRep.insertAnswerForQuestion(con, answer);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_ANSWER_FOR_QUESTION, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ANSWER_FOR_QUESTION, ex);
		} finally {
			DBUtils.close(con);
		}
		return answer;
	}
	
	public List<AnswerForQuestion> findAnswerForQuestions() throws AppException{
		List<AnswerForQuestion> answers = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			answers = answerForQuestionRep.findAllAnswers(con);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
		} finally {
			DBUtils.close(con);
		}
		return answers;
	}
	
	public List<AnswerForQuestion> findAnswerForQuestionsByTaskId(int taskId) throws AppException{
		List<AnswerForQuestion> answers = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			answers = answerForQuestionRep.findAnswerForQuestionsByTaskId(con, taskId);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
		} finally {
			DBUtils.close(con);
		}
		return answers;
	}
	
	public List<Course> findCourses() throws AppException{
		List<Course> courses = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courses = courseRep.findAllCourses(con);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			DBUtils.close(con);
		}
		return courses;
	}
	
	public List<Course> findCoursesByTeacherID(Integer id) throws AppException{
		List<Course> courses = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courses = courseRep.findCoursesByTeacherId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSES, ex);
		} finally {
			DBUtils.close(con);
		}
		return courses;
	}
	
	public Course findCourseById(int id) throws AppException {
		Course course = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			course = courseRep.findCourseById(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return course;
	}
	
	public Task findTaskByPublicationId(int id) throws AppException {
		Task task = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			task = taskRep.findTaskByPublicationId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_TASK_BY_PUBLICATION_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_TASK_BY_PUBLICATION_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return task;
	}
	
	public List<Course> searchInCourses(String str) throws AppException{
		List<Course> courses = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courses = courseRep.searchInCourses(con, str);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
		} finally {
			DBUtils.close(con);
		}
		return courses;
	}
	
	public void updateCourse(Course course) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			courseRep.updateCourseById(con, course);
			con.commit();
		} catch (SQLException ex ) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		
	}
	
	public List<Publication> findPublicationsByCourseId(int id) throws AppException{
		List<Publication> publications = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			publications = publicationRep.findPublicationsByCourseId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
		} finally {
			DBUtils.close(con);
		}
		return publications;
	}
	
	public Publication insertPublication(Publication publication) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			publication = publicationRep.insertPublication(con, publication);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return publication;
	}
	
	public Publication findPublicationByPositionAndCourse(int position, int courseId) throws AppException {
	    Publication publication = null;
	    Connection con=null;
	    try {
	      con = dbManager.getConnection();
	      publication = publicationRep.findPublicationByPositionAndCourse(con, position, courseId);
	      con.commit();
	    } catch (SQLException ex) {
	      DBUtils.rollback(con);
	      LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
	      throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
	    } finally {
	      DBUtils.close(con);
	    }
	    return publication;
	  }
	
	public Publication findPublicationByPosition(int id) throws AppException {
		Publication publication = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			publication = publicationRep.findPublicationByPosition(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COURSE_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return publication;
	}
	
	public List<UserAnswer> findUserAnswersByUserIdTaskId(int userId, int taskId) throws AppException{
		List<UserAnswer> answers = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			answers = UserAnswerRep.findUserAnswerByUserIdTaskId(con, userId, taskId);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWERS, ex);
		} finally {
			DBUtils.close(con);
		}
		return answers;
	}
	
	public Question GetQuestionById(List<Question> questions, int id)
	{
		for (Question question: questions)
		{
			if (question.getId() == id)
			{
				return question;
			}
		}
		return null;
	}
	
	public int markForTask(Task task, User user) throws AppException
	{
		List<AnswerForQuestion> answers = findAnswerForQuestionsByTaskId(task.getId());
		List<UserAnswer> uAnswers = findUserAnswersByUserIdTaskId(user.getId(), task.getId());
		List<Question> questions = findQuestionsByTaskId(task.getId());
		
		int question = answers.get(0).getQuestionId(), mark = 0, count = 0;
		boolean correct = true, done = false;
		for (AnswerForQuestion ans: answers)
		{
			if (ans.getQuestionId() != question)
			{
				question = ans.getQuestionId();
				if (correct && count != 0)
				{
					mark += GetQuestionById(questions, question).getMark();
				}
				correct = true;
				count = 0;
			}
			for (UserAnswer uAns: uAnswers)
			{
				if (ans.getId() == uAns.getAnswerForQuestionId())
				{
					if (!ans.isCorrect())
					{
						correct = false;
					}
					++count;
					done = true;
				}
			}
		}
		if (correct && count != 0)
		{
			mark += GetQuestionById(questions, question).getMark();
		}
		if (!done)
		{
			return -1;
		}
		return mark;
	}
	
	public int maxMarkForTask(Task task) throws AppException
	{
		List<AnswerForQuestion> answers = findAnswerForQuestionsByTaskId(task.getId());
		List<Question> questions = findQuestionsByTaskId(task.getId());
		
		int question = answers.get(0).getQuestionId(), maxMark = GetQuestionById(questions, question).getMark();
		for (AnswerForQuestion ans: answers)
		{
			if (ans.getQuestionId() != question)
			{
				question = ans.getQuestionId();
				maxMark += GetQuestionById(questions, question).getMark();
			}
		}
		return maxMark;
	}
	
	public List<Task> getTasksByCourse(Course course) throws DBException
	{
		List<Task> tasks = null;
		Connection con = null;
		try {
			con = dbManager.getConnection();
			tasks = taskRep.getTasksByCourseId(con, course.getId());
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
		} finally {
			DBUtils.close(con);
		}
		return tasks;
	}
}
