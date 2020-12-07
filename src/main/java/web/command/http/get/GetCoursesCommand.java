package web.command.http.get;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Course;
import model.Subscription;
import model.User;
import modelView.CourseView;
import service.CourseService;
import service.ProfileService;
import service.SubscriptionService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetCoursesCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetCoursesCommand.class);
	
	private CourseService courseServ;
	private ProfileService profileService;
	private SubscriptionService subServ;
	
	public GetCoursesCommand(CourseService courseServ, ProfileService profileService, SubscriptionService subServ) {
		super();
		this.courseServ = courseServ;
		this.profileService = profileService;
		this.subServ = subServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession(false);

		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_MAIN);
		
		List<Course> thisCourses = courseServ.findCourses();
		
		List<CourseView> thisCoursesView = new ArrayList<CourseView>();
		
		for (Course course : thisCourses)
		{
			User user = profileService.findUserById(course.getTeacherId());
			List<Subscription> subscription = subServ.findSubByCourseId(course.getId());
			String userName = user.getName();
			
			CourseView thisCoursetView = new CourseView();
			thisCoursetView.setId(course.getId());
			thisCoursetView.setTeacherName(userName);
			thisCoursetView.setDescription(course.getDescription());
			thisCoursetView.setName(course.getName());
			thisCoursetView.setTeacherId(course.getTeacherId());
			thisCoursetView.setPrice(course.getPrice());
			thisCoursetView.setPicture(course.getPicture());
			
			int k = 0;
			for(Subscription sb: subscription) {
				if(sb.getCourseId()==course.getId()) {
					k++;
				}
			}
			thisCoursetView.setCountFollowers(k);
			thisCoursesView.add(thisCoursetView);
		}
		
		session.setAttribute("courses", thisCoursesView);
		LOG.debug("Command finished");
		return cr;
	}
}



