package web.command.http.get;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetMainCommand implements Command{
	private static final Logger LOG = Logger.getLogger(GetMainCommand.class);
	
	
	public GetMainCommand() {
		super();
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_MAIN);
		int methodMain = 1;
		session.setAttribute("methodMain", methodMain);
		LOG.trace("Set the session attribute: methodMain --> " + methodMain);
		LOG.debug("Command finished");
		return cr;
	}

}
