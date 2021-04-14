package framework.controllers;

import framework.controllers.commands.AbstractAction;
import framework.controllers.results.IResult;

public class CommandManager {
	public IResult submit(AbstractAction action) {
		return action.execute();
	}
}
