package web.command.http.get;

import java.util.List;

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

public class GetSearchedCourseCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetSearchedCourseCommand.class);

	private CourseService courseServ;
	
	public GetSearchedCourseCommand(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession(false);

		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_MAIN);
		String str = request.getParameter("search");
		System.out.println("hihihihihi");
		System.out.println(str);
		List<Course> thisCourses = courseServ.searchInCourses(str);
		session.setAttribute("courses", thisCourses);
		LOG.debug("Command finished");
		return cr;
	}
}



