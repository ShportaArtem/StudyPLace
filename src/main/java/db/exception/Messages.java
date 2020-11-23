package db.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author A.Shporta
 *
 */
public class Messages {

	private Messages() {
	}
	
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = "Cannot obtain user order beans";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_USERS = "Cannot obtain users";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	
	public static final String ERR_CANNOT_INSERT_USER = "Cannot registration user";
	
	public static final String ERR_CANNOT_OBTAIN_COURSES = "Cannot obtain COURSES LIST";
	
	public static final String ERR_CANNOT_OBTAIN_COURSE_BY_ID = "Cannot obtain a course by its id";

	public static final String ERR_CANNOT_OBTAIN_COMMENTS = "Cannot obtain COMMENTS LIST";

	public static final String ERR_CANNOT_INSERT_SUBSCRIPTION = "Cannot INSERT SUBSCRIPTION";
}