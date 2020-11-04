package web.command;

import java.util.HashMap;
import java.util.Map;
/**
 * Abstract class for the CommandDispatcher extended.
 * Holder for all commands.
 * 
 * @author A.Shporta
 * 
 */
public abstract class CommandDispatcher {
	protected Map<String, Command> commandMap;
	protected Command defaultCommand;
	
	public CommandDispatcher( Command defaultCommand) {
		this.commandMap = new HashMap<>();
		this.defaultCommand = defaultCommand;
	}

	/**
	 * Get default command
	 * @return command object
	 */
	public Command getDefaultCommand() {
		return defaultCommand;
	}
	
	/**
	 * Set default command
	 * 
	 * @param defaultCommand
	 */
	public void setDefaultCommand(Command defaultCommand) {
		this.defaultCommand = defaultCommand;
	}
	
	/**
	 * Insert command to dispatcher with name
	 * @param commandName
	 * @param command
	 */
	public abstract boolean addCommand(String commandName, Command command);
	
	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public abstract Command getCommand(String commandName);
}