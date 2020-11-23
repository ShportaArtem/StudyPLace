package web.command.http.post;


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

public class UpdateCourseCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(UpdateCourseCommand.class);
	
	private CourseService courseService;
	
	public UpdateCourseCommand(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		//int courseId = Integer. parseInt(request.getParameter("courseId"));
		//System.out.print("курс АЙДИ - " + courseId);
		
		Course thisCourse = (Course) session.getAttribute("thisCourse");
		System.out.print("курс АЙДИ - " + thisCourse.getId());
		
		if(!"".equals(request.getParameter("name"))) {
			thisCourse.setName(request.getParameter("name"));
		}
		if(!"".equals(request.getParameter("description"))) {
			thisCourse.setDescription(request.getParameter("description"));
		}
		
		if(request.getParameter("price")==null || request.getParameter("price").equals("")) {
			thisCourse.setPrice(null);
		}
		else {
			thisCourse.setPrice(Double.parseDouble(request.getParameter("price")));
		}
		
		courseService.updateCourse(thisCourse);
		session.setAttribute("thisCourse", thisCourse);
		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_COURSES_POST);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
