package web.command.http.get;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class OpenAddQuestionCommand implements Command{

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
		CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_ADD_QUESTION);
	
		return cr;
	}
}
