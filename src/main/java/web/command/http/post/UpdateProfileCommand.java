package web.command.http.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.User;
import model.UserInfo;
import service.ProfileService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class UpdateProfileCommand implements Command {
	
	private static Logger LOG = Logger.getLogger(UpdateProfileCommand.class);
	
	private ProfileService profileService ;
	
	public UpdateProfileCommand(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		User userNow = (User) session.getAttribute("user");
		UserInfo userInfoNow = (UserInfo) session.getAttribute("thisUserInfo");
		if(userInfoNow==null) {
			userInfoNow = new UserInfo();
		}
		
		if(!"".equals(request.getParameter("username"))) {
			userNow.setLogin(request.getParameter("username"));
			
		}
		if(!"".equals(request.getParameter("name"))) {
			userNow.setName(request.getParameter("name"));
		}
		if(!"".equals(request.getParameter("surname"))) {
			userNow.setSurname(request.getParameter("surname"));
		}
		
		
		if(!"".equals(request.getParameter("about"))) {
			System.out.println(request.getParameter("about"));
			userInfoNow.setAbout(request.getParameter("about"));
		}
		else {
			userInfoNow.setAbout("");
		}
		
		if(!"".equals(request.getParameter("e-mail"))) {
			System.out.println(request.getParameter("e-mail"));
			userInfoNow.setEmail(request.getParameter("e-mail"));
		}
		else {
			userInfoNow.setEmail("");
		}
		
		if(!"".equals(request.getParameter("messanger"))) {
			userInfoNow.setMessanger(request.getParameter("messanger"));
		}
		else {
			userInfoNow.setMessanger("");
		}
		
		if(!"".equals(request.getParameter("website"))) {
			userInfoNow.setWebsite(request.getParameter("website"));
		}
		else {
			userInfoNow.setWebsite("");
		}
		
		if(!"".equals(request.getParameter("picture"))) {
			System.out.println(request.getParameter("picture"));
			userInfoNow.setPicture(request.getParameter("picture"));
		}
		
		if(userNow.getInfoId().equals(0)) {
			userInfoNow = profileService.insertUserInfo(userInfoNow);
			profileService.updateUser(userNow, userInfoNow.getId());
		    }else {
		      profileService.updateUserInfo(userInfoNow);
		      profileService.updateUser(userNow);
		    }
		
		
		LOG.trace("Update user" );
		
		int methodProfile = 2;
		session.setAttribute("user", userNow);
		session.setAttribute("thisUserInfo", userInfoNow);
		request.getSession().setAttribute("methodProfile", methodProfile);
		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_PROFILE_POST);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
