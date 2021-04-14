package framework.controllers.commands;

import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.Entry;

public class Deposit extends LoggableAction {
	private Entry entry;
	private Account account;

	public Deposit(Entry entry, Account account) {
		super();
		this.account = account;
		this.entry = entry;
	}

	@Override
	public IResult execute() {
		account.addEntry(entry);
		// TODO check if result should be something else. such as if negetive.
		return new CurrentBalance(account.getBalance(), CurrentBalance.SUCCESS);
	}

	public Entry getEntry() {
		return entry;
	}

	public Account getAccount() {
		return account;
	}
}
