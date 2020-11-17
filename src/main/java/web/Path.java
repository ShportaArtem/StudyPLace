package web;


/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author A.Shporta
 * 
 */
public final class Path {
	
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String PAGE_ERROR_PAGE = "/view/jsp/error_page.jsp";
	public static final String PAGE_MAIN = "/view/jsp/main.jsp";
	public static final String PAGE_REGISTRATION = "/view/jsp/registration.jsp";
	public static final String PAGE_REGISTRATION_WITH_ERROR = "/StudyPlace/Controller?command=getRegistration&errorRegistr=true";
	public static final String PAGE_LOGIN_WITH_ERROR = "/StudyPlace/Controller?command=getSignIn&errorLogin=true";
	public static final String PAGE_UPDATE_PROFILE_WITH_ERROR = "/StudyPlace/Controller?command=getUpdateProfile&errorProfile=true";
	public static final String PAGE_MAIN_POST = "/StudyPlace/Controller?command=getMain";
	public static final String PAGE_PROFILE_POST = "/StudyPlace/Controller?command=getProfile";
	public static final String PAGE_PROFILE = "/view/jsp/profile.jsp";
	public static final String PAGE_UPDATE_PROFILE = "/view/jsp/updateProfile.jsp";
	public static final String PAGE_ADD_COURSE = "/view/jsp/addCourse.jsp";
	public static final String PAGE_COURSES_POST = "/StudyPlace/Controller?command=getCourses";

}