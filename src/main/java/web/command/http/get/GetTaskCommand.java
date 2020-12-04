package web.command.http.get;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.exception.AppException;
import db.exception.DBException;
import model.AnswerForQuestion;
import model.Publication;
import model.Question;
import model.Task;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetTaskCommand implements Command {

	private CourseService courseServ;

	public GetTaskCommand(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
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
		session.setAttribute("taskNow", task);
		session.setAttribute("questions", questions);
		session.setAttribute("answersForQuestions", answers);
		return cr;
	}

}
