package web.command.http.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.exception.AppException;
import db.exception.DBException;
import model.AnswerForQuestion;
import model.Question;
import model.Task;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class AddQuestionCommand implements Command{
	
	private CourseService courseServ;
	
	public AddQuestionCommand(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		HttpSession session = request.getSession(false);
		CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_TASK_POST);
		Task task = (Task)session.getAttribute("taskNow");
		Question question = new Question();
		question.setMark(Integer.parseInt(request.getParameter("mark")));
		question.setTaskId(task.getId());
		question.setQuestion(request.getParameter("question"));
		if(request.getParameter("numberOfCorrectAnswers").equals("one")) {
			question.setOneCorrectAnswer(true);
		}else {
			question.setOneCorrectAnswer(false);
		}
		question.setDescription(request.getParameter("description"));
		List<Question> questions = courseServ.findQuestionsByTaskId(task.getId());
		int position=0;
		for(Question q : questions) {
			if(q.getPosition()>position) {
				position = q.getPosition();
			}
		}
		question.setPosition(++position);
		question = courseServ.insertQuestion(question);
		AnswerForQuestion answer1 = new AnswerForQuestion();
		answer1.setQuestionId(question.getId());
		answer1.setValue(request.getParameter("value1"));
		if(request.getParameter("correct1").equals("Correct")) {
			answer1.setCorrect(true);
		}else {
			answer1.setCorrect(false);
		}
		answer1 = courseServ.insertAnswerForQuestion(answer1);
		
		AnswerForQuestion answer2 = new AnswerForQuestion();
		answer2.setQuestionId(question.getId());
		answer2.setValue(request.getParameter("value2"));
		if(request.getParameter("correct2").equals("Correct")) {
			answer2.setCorrect(true);
		}else {
			answer2.setCorrect(false);
		}
		answer2 = courseServ.insertAnswerForQuestion(answer2);
		
		AnswerForQuestion answer3 = new AnswerForQuestion();
		answer3.setQuestionId(question.getId());
		answer3.setValue(request.getParameter("value3"));
		if(request.getParameter("correct3").equals("Correct")) {
			answer3.setCorrect(true);
		}else {
			answer3.setCorrect(false);
		}
		answer3 = courseServ.insertAnswerForQuestion(answer3);
		
		AnswerForQuestion answer4 = new AnswerForQuestion();
		answer4.setQuestionId(question.getId());
		answer4.setValue(request.getParameter("value4"));
		if(request.getParameter("correct4").equals("Correct")) {
			answer4.setCorrect(true);
		}else {
			answer4.setCorrect(false);
		}
		answer4 = courseServ.insertAnswerForQuestion(answer4);
		return cr;
	}

}
