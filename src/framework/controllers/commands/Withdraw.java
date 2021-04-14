package framework.controllers.commands;

import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.IEntry;

public class Withdraw extends LoggableAction {
	private IEntry entry;
	private Account account;
	public Withdraw(IEntry entry, Account account) {
		this.entry = entry;
		this.account = account;
	}

	@Override
	public IResult execute() {
//		TODO This is a bit hacky to support withdrawal. This
//		 should be replaced with more graceful code.
		entry.setAmount(-1 * entry.getAmount());
		account.addEntry(entry);
		if (account.getCurrentBalance() < 0)
			return new CurrentBalance(account.getCurrentBalance(), CurrentBalance.NEGATIVE_BALANCE);
		return new CurrentBalance(account.getCurrentBalance(), CurrentBalance.SUCCESS);
	}

	@Override
	IEntry getEntry() {
		return entry;
	}

	@Override
	Account getAccount() {
		return account;
	}
}
