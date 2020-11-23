package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CommentRep;
import db.utils.DBUtils;
import model.Comment;

public class CommentService {
	
	private static final Logger LOG = Logger.getLogger(CommentService.class);

	private DBManager dbManager;
	private CommentRep commentRep;
	//private UserRep userRep;
	
	public CommentService(DBManager dbManager, CommentRep commentRep /*, UserRep userRep*/)  {
		this.dbManager = dbManager;
		this.commentRep= commentRep;
		//this.userRep = userRep;
	}
	

	public List<Comment> findCommentsByCourseId(int id) throws AppException{
		List<Comment> comments = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			comments = commentRep.findCommentsByCourseId(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMMENTS, ex);
		} finally {
			DBUtils.close(con);
		}
		return comments;
	}
	
	public Comment insertCommentCourse(Comment comment) throws AppException {
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			comment = commentRep.insertCommentCourse(con, comment);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return comment;
	}
}
