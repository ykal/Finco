package banking.persistance;

import banking.Bank;
import framework.models.Data;
import framework.models.account.Account;
import framework.observer.Observer;
import framework.persistence.ACCFile;

public class BankAccFile extends ACCFile {
	Bank bank;

	public BankAccFile(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void notifyObservers() {
		System.out.println("Notify is called");
		Data data = new Data();
		bank.populateModelColumns(data);
		accounts.forEach(account ->
				data.addRow(new Object[]{account.getId(),
						account.getOwner().getEmail(),
						account.getOwner().getName(),
						account.getOwner().getCity(),
						account.getOwner().getCustomerType(),
						account.getAccountType(),
						account.getCurrentBalance()}));
		observers.forEach((Observer o) -> o.update(data));
	}

	@Override
	public void updateAccount(Account account) {
		removeAccount(account.getId());
		addAccount(account);
	}
}
