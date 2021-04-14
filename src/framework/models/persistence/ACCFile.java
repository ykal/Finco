package framework.models.persistence;

import framework.models.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ACCFile {
	private List<Account> accounts;

	public ACCFile() {
		accounts = new ArrayList();
	}
	public ACCFile(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public void doAll(Consumer consumer) {
		for (Account account : accounts) {
			consumer.accept(account);
		}
	}
}