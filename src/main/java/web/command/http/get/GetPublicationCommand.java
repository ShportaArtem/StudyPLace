package web.command.http.get;


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

public class GetPublicationCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetPublicationCommand.class);
		
	private CourseService coursesService;
	
	public GetPublicationCommand(CourseService coursesService) {
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
		
		int coursePosition;
		try {
			coursePosition = Integer. parseInt(request.getParameter("coursePosition"));
		}
		catch(Exception e){
			coursePosition = (int) session.getAttribute("coursePosition");
		}
		Course thisCourse = coursesService.findCourseById(courseId);
		Publication publication = coursesService.findPublicationByPosition(coursePosition);
		Publication publicationNow;
		boolean hasMaterial;
		if(publication.getCourseID()==courseId) {
			publicationNow = coursesService.findPublicationByPosition(coursePosition);
			hasMaterial = true;
		}
		else {
			publicationNow = null;
			hasMaterial=false;
		}
		boolean hasPrevious;
		if(coursesService.findPublicationByPosition(coursePosition-1)!=null) {
			hasPrevious = true;
		}
		else {
			hasPrevious = false;
		}
		boolean hasNext;
		if(coursesService.findPublicationByPosition(coursePosition+1)!=null) {
			hasNext = true;
		}
		else {
			hasNext = false;
		}
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_PUBLICATION);
		session.setAttribute("publicationNow", publicationNow);
		session.setAttribute("courseId", courseId);
		session.setAttribute("thisCourse", thisCourse);
		session.setAttribute("hasNext", hasNext);
		session.setAttribute("hasPrevious", hasPrevious);
		session.setAttribute("hasMaterial", hasMaterial);
		LOG.debug("Command finished");
		return cr;
	}


}
