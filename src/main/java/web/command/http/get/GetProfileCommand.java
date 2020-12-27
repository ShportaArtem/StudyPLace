package web.command.http.get;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Certificate;
import model.User;
import model.UserInfo;
import modelView.CertificateView;
import service.CertificateService;
import service.CourseService;
import service.LoginService;
import service.ProfileService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetProfileCommand implements Command{

	private static final Logger LOG = Logger.getLogger(GetProfileCommand.class);
		
	private LoginService loginService;
	private ProfileService profileService;
	CertificateService certServ;
	CourseService courseServ;
	
	public GetProfileCommand(LoginService loginService, ProfileService profileService, CertificateService certServ, CourseService courseServ) {
		this.loginService = loginService;
		this.profileService = profileService;
		this.certServ = certServ;
		this.courseServ = courseServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession(true);
		User thisUser = (User) session.getAttribute("user");
		thisUser = loginService.findUserById(thisUser.getId());
		UserInfo thisUserInfo = profileService.findUserInfoById(thisUser.getInfoId());
		
		List<CertificateView> certificates = new ArrayList<>();
		List<Certificate> certs = certServ.getCertsByUser(thisUser);
		for (Certificate value: certs)
		{
			certificates.add(extractCertificateView(value, thisUser, courseServ));
		}
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_PROFILE);
		session.setAttribute("certificates", certificates);
		session.setAttribute("thisUserInfo", thisUserInfo);
		session.setAttribute("user", thisUser);
		LOG.debug("Command finished");
		return cr;
	}
	private static CertificateView extractCertificateView(Certificate certificate, User user, CourseService courseServ) throws AppException {
		CertificateView carV = new CertificateView();
		carV.setId(certificate.getId());
		carV.setMark(certificate.getMark());
		carV.setNameUser(user.getName());
		carV.setSurnameUser(user.getSurname());
		carV.setNameCourse(courseServ.findCourseById(certificate.getCourseId()).getName());
		
		return carV;
	}
}
