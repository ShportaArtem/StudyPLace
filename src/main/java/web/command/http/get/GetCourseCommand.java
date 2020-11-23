package web.command.http.get;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Comment;
import model.Course;
import model.Subscription;
import model.User;
import modelView.CommentView;
import modelView.CourseView;
import service.CommentService;
import service.CourseService;
import service.ProfileService;
import service.SubscriptionService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetCourseCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetCourseCommand.class);
		
	private CourseService coursesService;
	private CommentService commServ;
	private ProfileService profileServ;
	private SubscriptionService subServ;
	
	public GetCourseCommand(CourseService coursesService, CommentService commServ, ProfileService profileServ, SubscriptionService subServ) {
		super();
		this.coursesService = coursesService;
		this.commServ = commServ;
		this.profileServ = profileServ;
		this.subServ = subServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command get course starts");
		HttpSession session = request.getSession(true);
		
		
		int courseId;
		try {
			courseId = Integer. parseInt(request.getParameter("courseId"));
		}
		catch(Exception e){
			courseId = (int) session.getAttribute("courseId");
		}
		System.out.print("курс АЙДИ - " + courseId);

		Course thisCourse = coursesService.findCourseById(courseId);
		User thisUser = (User) session.getAttribute("user");
		List<Comment> thisComments = null;
		try {
			thisComments = commServ.findCommentsByCourseId(thisCourse.getId());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		List<CommentView> thisCommentsView = new ArrayList<CommentView>();
		
		for (Comment comment : thisComments)
		{
			User user = profileServ.findUserById(comment.getUserId());
			String userName = user.getName();
			CommentView thisCommentView = new CommentView();
			thisCommentView.setCourseID(comment.getCourseID());
			thisCommentView.setDateTime(comment.getDateTime());
			thisCommentView.setUserName(userName);
			thisCommentView.setText(comment.getText());
			thisCommentView.setCourseID(comment.getCourseID());
			
			thisCommentsView.add(thisCommentView);
		}
		
		User user = profileServ.findUserById(thisCourse.getTeacherId());
		List<Subscription> subscription = subServ.findSubByCourseId(thisCourse.getId());
		String userName = user.getName();
		
		CourseView thisCoursetView = new CourseView();
		thisCoursetView.setId(thisCourse.getId());
		thisCoursetView.setTeacherName(userName);
		thisCoursetView.setDescription(thisCourse.getDescription());
		thisCoursetView.setName(thisCourse.getName());
		thisCoursetView.setTeacherId(thisCourse.getTeacherId());
		thisCoursetView.setPrice(thisCourse.getPrice());
		
		int k = 0;
		boolean subscribed = false;
		for(Subscription sb: subscription) {
			if(sb.getCourseId()==thisCourse.getId()) {
				k++;
				if(sb.getUserId()==thisUser.getId()) {
					subscribed = true;
				}
			}
		}
		thisCoursetView.setCountFollowers(k);
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_COURSE);
		session.setAttribute("thisCourse", thisCoursetView);
		session.setAttribute("thisComments", thisCommentsView);
		session.setAttribute("thisUser", thisUser);
		session.setAttribute("subscribed", subscribed);
		LOG.debug("Command finished");
		return cr;
	}


}
