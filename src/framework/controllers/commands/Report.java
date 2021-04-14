package framework.controllers.commands;

import framework.controllers.results.DoneResult;
import framework.controllers.results.IResult;

public class Report extends AbstractAction {
	@Override
	public IResult execute() {
//		TODO Do reporting.
		System.out.println("Reported");
		return new DoneResult();
	}
}
