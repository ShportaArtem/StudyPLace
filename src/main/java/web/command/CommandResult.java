package web.command;

import web.controller.RequestType;

/**
 * The result of command
 * @author A.Shporta
 *
 */
public interface CommandResult {
	/**
	 * Get type of request
	 * @return requestType
	 */
	RequestType getType();
	/**
	 * Get address
	 * @return Address to go once the command is executed.
	 */
	String getResult();
}
