package framework.models.customer;

import framework.models.account.IAccount;

public interface ICustomer {
	void addAccount(IAccount account);
	void removeAccount(IAccount account);
	void emailCustomer();
}
