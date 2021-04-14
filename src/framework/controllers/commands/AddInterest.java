package framework.controllers.commands;

import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.persistence.ACCFile;
import framework.models.account.Account;

import java.util.function.Consumer;

public class AddInterest extends AbstractAction {
	private ACCFile accounts;

	public AddInterest(ACCFile accounts) {
		this.accounts = accounts;
	}

	@Override
	public IResult execute() {
		Consumer<Account> addInterests = new Consumer<Account>() {
			@Override
			public void accept(Account account) {
				Double currentBalance = account.getCurrentBalance();
				Double interest = account.getInterest();

				Double newBalance = currentBalance + (currentBalance*interest);
				account.setCurrentBalance(newBalance);
			}
		};

		accounts.doAll(addInterests);

		IResult result = new CurrentBalance(0, CurrentBalance.SUCCESS);
		return result;
	}

	public ACCFile getAccounts() {
		return accounts;
	}
}