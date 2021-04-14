package framework.controllers.commands;

import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.Entry;

public class Proxy extends LoggableAction {
	private LoggableAction laction;
	public Proxy(LoggableAction laction) {
		super();
		this.laction = laction;
	}

	@Override
	public IResult execute() {
		IResult result = laction.execute();
		addToHistory(result);
		return result;
	}

	@Override
	Entry getEntry() {
		return laction.getEntry();
	}

	@Override
	Account getAccount() {
		return laction.getAccount();
	}

	private void addToHistory(IResult result) {
//		TODO create new Report[using result and entry] and add to REPFile.
	}
}
