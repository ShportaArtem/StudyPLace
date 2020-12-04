package web.command.http.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.exception.AppException;
import db.exception.DBException;
import model.Publication;
import model.Task;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class AddTaskCommand implements Command{
	
	private CourseService courseServ;
	
	public AddTaskCommand(CourseService courseServ) {
		super();
		this.courseServ = courseServ;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		HttpSession session = request.getSession(false);
		CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_TASK);
		Publication publ = (Publication)session.getAttribute("publicationNow");
		Task task = new Task();
		task.setDescription(request.getParameter("description"));
		task.setName(request.getParameter("name"));
		task.setPublicationId(publ.getId());
		task = courseServ.insertTask(task);
		session.setAttribute("taskNow", task);
		return cr;
	}

}
