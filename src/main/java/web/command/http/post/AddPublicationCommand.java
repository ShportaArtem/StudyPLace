package web.command.http.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.Publication;
import service.CourseService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class AddPublicationCommand implements Command{
	private static Logger LOG = Logger.getLogger(AddPublicationCommand.class);
	
	private CourseService courseServ;
	
	public AddPublicationCommand(CourseService courseServ) {
		super();
		this.courseServ =  courseServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		int courseId;
		try {
			courseId = Integer. parseInt(request.getParameter("courseId"));
		}
		catch(Exception e){
			courseId = (int) session.getAttribute("courseId");
		}
		
		List<Publication> allPublications = new ArrayList<Publication>();
		try {
			allPublications = courseServ.findPublicationsByCourseId(courseId);
		}
		catch(Exception e) {
			allPublications = null;
		}
		int k = 1;
		for(@SuppressWarnings("unused") Publication p: allPublications) {
			k++;
		}
		
		Publication publication = new Publication();
		
		publication.setName(request.getParameter("name"));
		
		if(request.getParameter("description")==null || request.getParameter("description").equals("")) {
			publication.setDescription(null);	
		}else {
			publication.setDescription(request.getParameter("description"));
		}
		publication.setPosition(k);
		publication.setCourseID(courseId);
		courseServ.insertPublication(publication);

		CommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_PUBLICATIONS_POST);
		LOG.debug("Commands finished");
		return cr;
	}

}
