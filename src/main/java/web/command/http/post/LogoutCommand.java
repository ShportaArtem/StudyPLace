package web.command.http.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class LogoutCommand implements Command {


	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_LOGIN);

		LOG.debug("Command finished");
		return cr;
	}

}