package web;


/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author A.Shporta
 * 
 */
public final class Path {
	
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String PAGE_TASK = "/view/jsp/task.jsp";
	public static final String PAGE_ERROR_PAGE = "/view/jsp/error_page.jsp";
	public static final String PAGE_MAIN = "/view/jsp/main.jsp";
	public static final String PAGE_REGISTRATION = "/view/jsp/registration.jsp";
	public static final String PAGE_REGISTRATION_WITH_ERROR = "/StudyPlace/Controller?command=getRegistration&errorRegistr=true";
	public static final String PAGE_LOGIN_WITH_ERROR = "/StudyPlace/Controller?command=getSignIn&errorLogin=true";
	public static final String PAGE_UPDATE_PROFILE_WITH_ERROR = "/StudyPlace/Controller?command=getUpdateProfile&errorProfile=true";
	public static final String PAGE_MAIN_POST = "/StudyPlace/Controller?command=getCourses";
	public static final String PAGE_TASK_POST = "/StudyPlace/Controller?command=getTask";
	public static final String PAGE_PROFILE_POST = "/StudyPlace/Controller?command=getProfile";
	public static final String PAGE_PROFILE = "/view/jsp/profile.jsp";
	public static final String PAGE_UPDATE_PROFILE = "/view/jsp/updateProfile.jsp";
	public static final String PAGE_ADD_COURSE = "/view/jsp/addCourse.jsp";
	public static final String PAGE_COURSES_POST = "/StudyPlace/Controller?command=getCourses";
	public static final String PAGE_COURSE_POST = "/StudyPlace/Controller?command=getCourse";
	public static final String PAGE_COURSE = "/view/jsp/course.jsp";
	public static final String PAGE_UPDATE_PROFILE_POST = "/StudyPlace/Controller?command=updateCourse";
	public static final String PAGE_UPDATE_COURSE = "/view/jsp/update_course.jsp";
	public static final String PAGE_ADD_PUBLICATION = "/view/jsp/addPublication.jsp";
	public static final String PAGE_ADD_TASK = "/view/jsp/addTask.jsp";
	public static final String PAGE_ADD_QUESTION = "/view/jsp/addQuestion.jsp";
	public static final String PAGE_ADD_PUBLICATION_POST = "/StudyPlace/Controller?command=addPublication";
	public static final String PAGE_PUBLICATIONS = "/view/jsp/publications.jsp";
	public static final String PAGE_PUBLICATIONS_POST = "/StudyPlace/Controller?command=getPublications";
	public static final String PAGE_PUBLICATION = "/view/jsp/publication.jsp";
	public static final String PAGE_PUBLICATION_POST = "/StudyPlace/Controller?command=getPublication";
}