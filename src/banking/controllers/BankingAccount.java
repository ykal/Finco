package banking.controllers;

import framework.models.account.Account;
import framework.models.account.IEntry;

public abstract class BankingAccount extends Account {
	private Long accnr;

	public void addEntry(IEntry entry) {
		super.addEntry(entry);
	}
}
