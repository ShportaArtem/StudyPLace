package web.command.http;

import web.command.Command;
import web.command.CommandDispatcher;

public class HttpCommandDispatcher extends CommandDispatcher{

	public HttpCommandDispatcher(Command defaultCommand) {
		super(defaultCommand);
	}

	@Override
	public boolean addCommand(String commandName, Command command) {
		return commandMap.put(commandName, command)==null;
	}
	
	
	@Override
	public Command getCommand(String commandName) {
		
		return commandMap.getOrDefault(commandName, defaultCommand);
	}

}
