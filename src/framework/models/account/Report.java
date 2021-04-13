package framework.models.account;

import framework.models.account.Account;
import framework.models.account.Entry;

public class Report {
	private Float prevBalance;
	private Account account;
	private Entry entry;

	public Float getPrevBalance() {
		return prevBalance;
	}
	public void setPrevBalance(Float prevBalance) {
		this.prevBalance = prevBalance;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}
