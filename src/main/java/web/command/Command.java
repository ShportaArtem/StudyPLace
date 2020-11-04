package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.exception.AppException;
import db.exception.DBException;
/**
 * Main interface for the Command pattern implementation.
 * 
 * @author A.Shporta
 * 
 */
public interface Command {
	
	/**
	 * Execution method for command.
	 * 
	 * @return CommmandResult.
	 */
	CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException;
}
