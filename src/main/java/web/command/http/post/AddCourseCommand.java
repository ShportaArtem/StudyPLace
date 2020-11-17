package web.command.http.post;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Course;
import model.User;
import service.CourseService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class AddCourseCommand implements Command{
	private static Logger LOG = Logger.getLogger(AddCourseCommand.class);
	
	private CourseService courseServ;
	
	public AddCourseCommand(CourseService courseServ) {
		super();
		this.courseServ =  courseServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		Course course = new Course();
		course.setDescription(request.getParameter("description"));
		
		try {
			course.setPassword(new String(HashUtil.getSHA(request.getParameter("password"))));
			
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		course.setName(request.getParameter("name"));
		if(request.getParameter("price")==null || request.getParameter("price").equals("")) {
			course.setPrice(null);	
		}else {
			course.setPrice(Double.parseDouble(request.getParameter("price")));
		}
		User user = (User) session.getAttribute("user");
		course.setTeacherId(user.getId());
		course.setPicture(null);
		courseServ.insertCourse(course);

		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_COURSES_POST);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
