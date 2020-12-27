package web.command.http.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.exception.AppException;
import db.exception.DBException;
import model.AnswerForQuestion;
import model.Course;
import model.Publication;
import model.Question;
import model.Task;
import model.User;
import model.UserAnswer;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class EndTaskCommand implements Command {

	private CourseService courseServ;

	public EndTaskCommand(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		HttpSession session = request.getSession(false);
		CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_TASK);
		Publication publ = (Publication) session.getAttribute("publicationNow");
		Course course = (Course) session.getAttribute("thisCourse");
		
		User user = (User) session.getAttribute("user");
		Task task = courseServ.findTaskByPublicationId(publ.getId());
		List<Question> questions = courseServ.findQuestionsByTaskId(task.getId());
		questions.sort((Question arg0, Question arg1) -> arg1.getPosition() - arg0.getPosition());
		List<AnswerForQuestion> answers = courseServ.findAnswerForQuestionsByTaskId(task.getId());
		String[] data = request.getParameterValues("answerChB");
		UserAnswer item = new UserAnswer();
		if (data!=null)
		{
			for(String value: data)
			{
				item = new UserAnswer();
				for(AnswerForQuestion ans: answers)
				{
					if (ans.getId() == Integer.parseInt(value))
					{
						item.setUserId(user.getId());
						item.setAnswerForQuestionId(ans.getId());
						item = courseServ.insertUserAnswer(item);
					}
				}
			}
			cr = new HttpCommandResult(RequestType.POST,Path.PAGE_TASK_POST);
		}
		else {
			System.out.println("Null");
		}
		
		
		return cr;
	}

}
