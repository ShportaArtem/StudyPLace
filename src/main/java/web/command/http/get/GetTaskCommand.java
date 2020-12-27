package web.command.http.get;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.exception.AppException;
import db.exception.DBException;
import model.AnswerForQuestion;
import model.Certificate;
import model.Course;
import model.Publication;
import model.Question;
import model.Task;
import model.User;
import service.CertificateService;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetTaskCommand implements Command {

	private CourseService courseServ;
	private CertificateService certificateServ;

	public GetTaskCommand(CourseService courseServ, CertificateService certificateServ) {
		super();
		this.courseServ = courseServ;
		this.certificateServ = certificateServ;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		HttpSession session = request.getSession(false);
		CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_TASK);
		Publication publ = (Publication) session.getAttribute("publicationNow");
		Task task = courseServ.findTaskByPublicationId(publ.getId());
		List<Question> questions = courseServ.findQuestionsByTaskId(task.getId());
		questions.sort((Question arg0, Question arg1) -> arg1.getPosition() - arg0.getPosition());
		List<AnswerForQuestion> answers = courseServ.findAnswerForQuestions();
		boolean hasNext=false;
		if(courseServ.findPublicationByPosition(publ.getPosition()+1)!=null) {
			hasNext = true;
		}
		User user = (User) session.getAttribute("thisUser");
		Course course = (Course) session.getAttribute("thisCourse");
		int mark = -1, maxMark = -1;
		if (user.getId() != course.getTeacherId())
		{
			mark = courseServ.markForTask(task, user);
			maxMark = courseServ.maxMarkForTask(task);
		}
		boolean temp = false;
		List<Task> allTasks = courseServ.getTasksByCourse(course);
		Certificate cert = certificateServ.getCertByCourseAndUser(user, course);
		System.out.println("UserCourse "+ user.getId() + "___"+course.getId());
		int courseMark = 0, courseMaxMark = 0;
		if (user.getId() != course.getTeacherId())
		{
			System.out.println("I`M HERE!!!!!!!!!!!!!!!!!!!!!!!");
			if (cert==null)
				System.out.println("cert is null");
			System.out.println("tasks count " + allTasks.size());
			for (Task value: allTasks)
			{
				courseMark += courseServ.markForTask(value, user);
				courseMaxMark += courseServ.maxMarkForTask(value);
				if (courseServ.markForTask(value, user) == -1)
				{
					session.setAttribute("CourseEnded", false);
					temp = true;
				}
			}
			System.out.println("mark " + courseMark);
			System.out.println("maxMark " + courseMaxMark);
			if (!temp)
			{
				System.out.println("I`M HERE!!!!!!!!!!!!!!!!!!!!!!!");
				session.setAttribute("CourseEnded", true);
				if (cert == null)
				{
					System.out.println("I`M HERE!!!!!!!!!!!!!!!!!!!!!!!");
					cert = certificateServ.createCertificate(course.getId(), user.getId(), (courseMark * 10000) / courseMaxMark);
				}
			}
		}
		
		session.setAttribute("mark", mark);
		session.setAttribute("maxMark", maxMark);
		session.setAttribute("hasNext", hasNext);
		session.setAttribute("taskNow", task);
		session.setAttribute("emptyString", "");
		session.setAttribute("questions", questions);
		session.setAttribute("answersForQuestions", answers);
		return cr;
	}

}
