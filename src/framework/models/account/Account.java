package framework.models.account;

import framework.models.customer.*;

public class Account {
	private ICustomer owner;

	public void addEntry(Entry entry) {
//		TODO add amount to this account.
	}

	public double getBalance() {
		throw new RuntimeException("getBalance Not implemented yet");
	}

	public ICustomer getOwner() {
		return owner;
	}
}
