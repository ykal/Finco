package framework.models.customer;

import java.util.List;
import framework.models.account.IAccount;

public abstract class Customer implements ICustomer {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String email;
	private List<IAccount> accounts;

	public void addAccount(IAccount account) {
		this.accounts.add(account);
	}
	public void removeAccount(IAccount account) {
		this.accounts.remove(account);
	}
	public void emailCustomer() {
		System.out.println("mailto:"+this.email);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<IAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<IAccount> accounts) {
		this.accounts = accounts;
	}
}
