package web.command.http.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Subscription;
import model.User;
import service.SubscriptionService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class SubscribeForCourseCommand implements Command{
	private static Logger LOG = Logger.getLogger(SubscribeForCourseCommand.class);
	
	private SubscriptionService subsServ;
	
	public SubscribeForCourseCommand(SubscriptionService subsServ) {
		super();
		this.subsServ =  subsServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		Subscription subs = new Subscription();
		int courseId;
		try {
			courseId = Integer. parseInt(request.getParameter("courseId"));
		}
		catch(Exception e){
			courseId = (int) session.getAttribute("courseId");
		}
		System.out.print("курс АЙДИ - " + courseId);
		User user = (User) session.getAttribute("user");
		System.out.print("user АЙДИ - " + user.getId());
		
		subs.setCourseId(courseId);;
		subs.setUserId(user.getId());;
		subsServ.insertSubs(subs);

		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_COURSE_POST);
		session.setAttribute("courseId", courseId);
		LOG.debug("Commands finished");
		return cr;
	}

}
