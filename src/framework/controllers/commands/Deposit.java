package framework.controllers.commands;

import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.Entry;
import framework.models.account.IEntry;

public class Deposit extends LoggableAction {
	private IEntry entry;
	private Account account;

	public Deposit(IEntry entry, Account account) {
		super();
		this.account = account;
		this.entry = entry;
	}

	@Override
	public IResult execute() {
		account.addEntry(entry);
		// TODO check if result should be something else. such as if negetive.
		return new CurrentBalance(account.getCurrentBalance(), CurrentBalance.SUCCESS);
	}

	public IEntry getEntry() {
		return entry;
	}

	public Account getAccount() {
		return account;
	}
}
