package framework.controllers.commands;

import framework.controllers.results.DoneResult;
import framework.controllers.results.IResult;

public class ENotify extends AbstractAction {
	@Override
	public IResult execute() {
		emailAccount();
		return new DoneResult();
	}

	private void emailAccount() {
//		TODO do something interesting.
		System.out.println("Email Customer");
	}
}

