package web.command.http.post;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.User;
import service.RegistrationService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class RegistrationCommand implements Command{

private static Logger LOG = Logger.getLogger(LoginCommand.class);

	private RegistrationService registrServ;
	
	public RegistrationCommand(RegistrationService registrServ) {
		super();
		this.registrServ = registrServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin(request.getParameter("loginUser"));
		LOG.trace("Found in request parameters: login --> " + user.getLogin());
		
		try {
			user.setPassword(new String(HashUtil.getSHA(request.getParameter("passwordUser"))));
			
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		user.setRoleId(2);
		user.setName(request.getParameter("nameUser"));
		LOG.trace("Found in request parameters: name --> " + user.getName());
		
		user.setSurname(request.getParameter("surnameUser"));
		LOG.trace("Found in request parameters: surname --> " + user.getSurname());
		
		user.setInfoId(null);
		
		registrServ.insertUser(user);
		LOG.trace("Insert in DB user --> " + user);
		
		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_MAIN_POST);
		int methodMain = 2;
		session.setAttribute("methodMain", methodMain);
		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: methodMain --> " + methodMain);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
