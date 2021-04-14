package framework.models.account;

import framework.models.customer.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	private Integer id;
	private Double currentBalance;
	private ICustomer owner;
	private List<IEntry> entries;

	public Account() {
		entries = new ArrayList();
	}

	public abstract void addInterest();
	public abstract Double getInterest();

	public void addEntry(IEntry entry) {
		this.entries.add(entry);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Double balance) {
		this.currentBalance = balance;
	}
	public ICustomer getOwner() {
		return owner;
	}
	public void setOwner(ICustomer owner) {
		this.owner = owner;
	}
	public List<IEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<IEntry> entries) {
		this.entries = entries;
	}
}
