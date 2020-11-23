package web.command.http.post;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Comment;
import model.User;
import service.CommentService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class LeaveCommentCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(LeaveCommentCommand.class);
	
	private CommentService commentService ;
	
	public LeaveCommentCommand(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command comment starts");
		
		HttpSession session = request.getSession(true);
		int courseId = Integer. parseInt(request.getParameter("courseId"));
		System.out.print("курс АЙДИ - " + courseId);
		
		
		User userNow = (User) session.getAttribute("user");
		System.out.print("ЮЗЕР АЙДИ - " + userNow.getId());
		Comment com = new Comment();
		com.setUserId(userNow.getId());
		com.setText(request.getParameter("text"));
		com.setCourseID(courseId);
		if(!"".equals(request.getParameter("text"))){
			commentService.insertCommentCourse(com);
		}
		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_COURSE_POST);
		session.setAttribute("courseId", courseId);
		LOG.debug("Commands finished");
		return cr;
	}

}
