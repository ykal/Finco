package banking.persistance;

import framework.FinCo;
import framework.models.Data;
import framework.models.account.Account;
import framework.observer.Observer;
import framework.persistence.ACCFile;

public class BankAccFile extends ACCFile {
	FinCo app;

	public BankAccFile(FinCo app) {
		this.app = app;
	}

	@Override
	public void notifyObservers() {
		System.out.println("Notify is called");
		Data data = new Data();
		app.populateModelColumns(data);
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
