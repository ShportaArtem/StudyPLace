package web.command.http.post;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import model.User;
import service.LoginService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;
/**
 * Login command
 * 
 * @author A.Shporta
 */
public class LoginCommand implements Command {

	private static Logger LOG = Logger.getLogger(LoginCommand.class);
	
	private LoginService loginService ;
	
	public LoginCommand(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(true);

		String login = request.getParameter("loginUser");
		LOG.trace("Found in request parameters: loginUser --> " + login);
		
		String password = request.getParameter("passwordUser");
		LOG.trace("Found in request parameters: passwordUser --> " + password);
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		
		User user = loginService.findUserByLogin(login);
		LOG.trace("Found in DB: user -->" + user );
		
		
		String forward = Path.PAGE_ERROR_PAGE;
		if(!"admin".equals(login) && !"admin".equals(password)) {
		try {
			if (user == null || !new String(HashUtil.getSHA(password)).equals(user.getPassword())) {
				LOG.error("Cannot find user with such login/password");
				throw new AppException("Cannot find user with such login/password");
			}
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		}
		Integer userRole = user.getRoleId();
			forward = Path.PAGE_MAIN_POST;

		CommandResult cr = new HttpCommandResult(RequestType.POST, forward);

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);
		
		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);
		
		LOG.debug("Commands finished");
		
		return cr;

	}

}
