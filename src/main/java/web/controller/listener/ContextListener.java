package web.controller.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import db.DBManager;
import db.exception.DBException;
import db.repository.AnswerForQuestionRep;
import db.repository.CertificateRep;
import db.repository.CommentRep;
import db.repository.CourseRep;
import db.repository.PublicationRep;
import db.repository.QuestionRep;
import db.repository.SubscriptionRep;
import db.repository.TaskRep;
import db.repository.UserInfoRep;
import db.repository.UserRep;
import db.repository.UserAnswerRep;
import service.CertificateService;
import service.CommentService;
import service.CourseService;
import service.LoginService;
import service.ProfileService;
import service.RegistrationService;
import service.SubscriptionService;
import web.command.http.HttpCommandDispatcher;
import web.command.http.get.DefaultCommand;
import web.command.http.get.GetAddPublicationCommand;
import web.command.http.get.GetCertificateCommand;
import web.command.http.get.GetCourseCommand;
import web.command.http.get.GetCoursesCommand;
import web.command.http.get.GetCoursesCreatedByUserCommand;
import web.command.http.get.GetCoursesForLearningCommand;
import web.command.http.get.GetMainCommand;
import web.command.http.get.GetProfileCommand;
import web.command.http.get.GetPublicationCommand;
import web.command.http.get.GetPublicationsCommand;
import web.command.http.get.GetRegistrationCommand;
import web.command.http.get.GetSignInCommand;
import web.command.http.get.GetTaskCommand;
import web.command.http.get.GetUpdateCourseCommand;
import web.command.http.get.GetUpdateProfileCommand;
import web.command.http.get.OpenAddCourseCommand;
import web.command.http.get.OpenAddQuestionCommand;
import web.command.http.get.OpenAddTaskCommand;
import web.command.http.post.AddCourseCommand;
import web.command.http.post.AddPublicationCommand;
import web.command.http.post.AddQuestionCommand;
import web.command.http.post.AddTaskCommand;
import web.command.http.post.EndTaskCommand;
import web.command.http.post.LeaveCommentCommand;
import web.command.http.post.LoginCommand;
import web.command.http.post.LogoutCommand;
import web.command.http.post.RegistrationCommand;
import web.command.http.post.SubscribeForCourseCommand;
import web.command.http.post.UpdateCourseCommand;
import web.command.http.post.UpdateProfileCommand;


/**
 * Context listener.
 * 
 * @author A.Shporta
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");
		ServletContext context = event.getServletContext();
		String localesFileName = context.getInitParameter("locales");

		initLog4J(context);
		// obtain reale path on server
		String localesFileRealPath = context.getRealPath(localesFileName);
		// locad descriptions
		Properties locales = new Properties();
		try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// save descriptions to servlet context
		context.setAttribute("locales", locales);
		locales.list(System.out);

		initContext(context);
		log("Servlet context initialization finished");
	}
	
	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}
	
	/**
	 * Initializes CommandDispatcher with repositories and services.
	 * 
	 * @param servletContext
	 */
	private void initContext(ServletContext context) {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager();
		} catch (DBException e) {
			LOG.error(e);
		}
		
		UserRep userRep = new UserRep();
		UserInfoRep userInfoRep = new UserInfoRep();
		CourseRep courseRep = new CourseRep();
		CommentRep commentRep = new CommentRep();
		SubscriptionRep subsRep= new SubscriptionRep();
		PublicationRep publicationRep = new PublicationRep();
		TaskRep taskRep = new TaskRep();
		QuestionRep questionRep = new QuestionRep();
		AnswerForQuestionRep answerForQuestionRep = new AnswerForQuestionRep();
		CertificateRep cartRep = new CertificateRep();
		UserAnswerRep userAnswerRep = new UserAnswerRep();
		
		
		LoginService loginService = new LoginService(dbManager, userRep);
		RegistrationService registrServ = new RegistrationService(dbManager, userRep);
		ProfileService profileService = new ProfileService(dbManager,userInfoRep, userRep);
		CourseService courseService = new CourseService(dbManager, courseRep, taskRep, questionRep, answerForQuestionRep, publicationRep, userAnswerRep);
		CommentService commentService = new CommentService(dbManager, commentRep);
		SubscriptionService subscribeService = new SubscriptionService(dbManager, subsRep);
		CertificateService certificateService = new CertificateService(dbManager, cartRep);
		
		HttpCommandDispatcher dispatcher = new HttpCommandDispatcher(new DefaultCommand());
		
		dispatcher.addCommand("login", new LoginCommand(loginService));
		dispatcher.addCommand("getRegistration", new GetRegistrationCommand());
		dispatcher.addCommand("getSignIn", new GetSignInCommand());
		dispatcher.addCommand("getMain", new GetMainCommand());
		dispatcher.addCommand("registration", new RegistrationCommand(registrServ, loginService));
		dispatcher.addCommand("getProfile", new GetProfileCommand(loginService, profileService, certificateService, courseService));
		dispatcher.addCommand("getUpdateProfile", new GetUpdateProfileCommand(loginService, profileService));
		dispatcher.addCommand("updateProfile", new UpdateProfileCommand(profileService));
		dispatcher.addCommand("addCourse", new AddCourseCommand(courseService));
		dispatcher.addCommand("openAddCourse", new OpenAddCourseCommand());
		dispatcher.addCommand("getCourses", new GetCoursesCommand(courseService, profileService, subscribeService));
		dispatcher.addCommand("getTask", new GetTaskCommand(courseService, certificateService));
		dispatcher.addCommand("getCourse", new GetCourseCommand(courseService, commentService, profileService, subscribeService, certificateService));
		dispatcher.addCommand("leaveComment", new LeaveCommentCommand(commentService));
		dispatcher.addCommand("logout", new LogoutCommand());
		dispatcher.addCommand("subscribeCourse", new SubscribeForCourseCommand(subscribeService));
		dispatcher.addCommand("updateCourse", new UpdateCourseCommand(courseService));
		dispatcher.addCommand("getUpdateCourse", new GetUpdateCourseCommand(courseService));
		dispatcher.addCommand("addPublication", new AddPublicationCommand(courseService));
		dispatcher.addCommand("addTask", new AddTaskCommand(courseService));
		dispatcher.addCommand("addQuestion", new AddQuestionCommand(courseService));
		dispatcher.addCommand("getAddPublication", new GetAddPublicationCommand());
		dispatcher.addCommand("openAddTask", new OpenAddTaskCommand());
		dispatcher.addCommand("openAddQuestion", new OpenAddQuestionCommand());
		dispatcher.addCommand("getPublications", new GetPublicationsCommand(courseService));
		dispatcher.addCommand("getPublication", new GetPublicationCommand(courseService));
		dispatcher.addCommand("getMyCourses", new GetCoursesCreatedByUserCommand(courseService, profileService, subscribeService));
		dispatcher.addCommand("getMyLearning", new GetCoursesForLearningCommand(courseService, profileService, subscribeService));
		dispatcher.addCommand("getCertificate", new GetCertificateCommand(certificateService, courseService));
		dispatcher.addCommand("endTask", new EndTaskCommand(courseService));
		
		
		context.setAttribute("dispatcher", dispatcher);
		
		
		LOG.debug("Command dispatcher was successfully initialized");
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
