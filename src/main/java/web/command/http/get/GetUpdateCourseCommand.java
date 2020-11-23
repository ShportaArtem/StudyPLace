package web.command.http.get;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Course;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetUpdateCourseCommand implements Command{
	
	private static Logger LOG = Logger.getLogger(GetUpdateCourseCommand.class);
	
	private CourseService coursesService;
	
	public GetUpdateCourseCommand(CourseService coursesService) {
		super();
		this.coursesService = coursesService;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
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
		CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_UPDATE_COURSE);
		session.setAttribute("thisCourse", thisCourse);
		LOG.debug("Commands finished");
		return cr;
	}
		
}
