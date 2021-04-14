package framework.models.customer;

import framework.models.account.IAccount;

public interface ICustomer {
	public static final String COMPANY = "Company";
	public static final String PERSON = "Person";
	void addAccount(IAccount account);
	void removeAccount(IAccount account);
	void emailCustomer();
	String getCustomerType();
}
