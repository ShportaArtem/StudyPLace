package web.command.http.get;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Course;
import model.Publication;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetPublicationsCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetPublicationsCommand.class);
		
	private CourseService coursesService;
	
	public GetPublicationsCommand(CourseService coursesService) {
		super();
		this.coursesService = coursesService;
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
		Course thisCourse = coursesService.findCourseById(courseId);
		List<Publication> publicationsNow = null;
		try {
			publicationsNow = coursesService.findPublicationsByCourseId(courseId);
		}
		catch(Exception e) {
			publicationsNow = null;
		}
		System.out.println(publicationsNow.size());
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_PUBLICATIONS);
		session.setAttribute("publicationsNow", publicationsNow);
		session.setAttribute("courseId", courseId);
		session.setAttribute("thisCourse", thisCourse);
		LOG.debug("Command finished");
		return cr;
	}


}
