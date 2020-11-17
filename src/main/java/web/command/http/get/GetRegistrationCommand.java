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
/**
 * Get registration command
 * 
 * @author A.Shporta
 */
public class GetRegistrationCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetRegistrationCommand.class);
	
	
	
	public GetRegistrationCommand() {
		super();
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		HttpSession session = request.getSession();
		if(request.getParameter("errorRegistr").equals("false")) {
			session.setAttribute("errorRegistr", false);
		}else {
			session.setAttribute("errorRegistr", true);
		}
		LOG.debug("Command starts");
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_REGISTRATION);
		LOG.debug("Command finished");
		return cr;
	}
	
}
