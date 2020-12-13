package web.command.http.get;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Certificate;
import model.User;
import modelView.CertificateView;
import service.CertificateService;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class GetCertificateCommand implements Command{
	
	private static final Logger LOG = Logger.getLogger(GetCertificateCommand.class);
	
	private CertificateService certServ;
	private CourseService courseServ;

	public GetCertificateCommand(CertificateService certServ, CourseService courseServ) {
		super();
		this.certServ = certServ;
		this.courseServ = courseServ;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command get course starts");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		Certificate certificate = certServ.findCertificateById(Integer.parseInt(request.getParameter("certificateId")));
		CertificateView cerV = extractCertificateView(certificate, user, courseServ);
		session.setAttribute("certificate", certificate);
		session.setAttribute("certificateView", cerV);
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_CERTIFICATE);
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
