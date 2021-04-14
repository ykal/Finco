package framework.controllers.commands;

import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.IEntry;
import framework.models.account.Report;
import framework.persistence.REPFile;

public class Proxy extends LoggableAction {
	private LoggableAction laction;
	private REPFile repFile;
	public Proxy(LoggableAction laction, REPFile repFile) {
		super();
		this.laction = laction;
		this.repFile = repFile;
	}

	@Override
	public IResult execute() {
		IResult result = laction.execute();
		addToHistory(result);
		return result;
	}

	@Override
	IEntry getEntry() {
		return laction.getEntry();
	}

	@Override
	Account getAccount() {
		return laction.getAccount();
	}

	private void addToHistory(IResult result) {
		Report report = new Report(laction.getEntry().getAmount(),
				laction.getAccount().getId(),
				laction.getEntry().getDate());
		repFile.addReport(report);
	}
}
