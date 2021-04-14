package framework.models.account;

import java.util.ArrayList;
import java.util.List;

import framework.models.customer.*;

public abstract class Account {
	private String id;
	private double currentBalance;
	private Customer owner;
	private List<IEntry> entries;

	public Account() {
		entries = new ArrayList();
	}

	public abstract void addInterest();
	public abstract double getInterest();

	public void addEntry(IEntry entry) {
		currentBalance += entry.getAmount();
		this.entries.add(entry);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double balance) {
		this.currentBalance = balance;
	}
	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public List<IEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<IEntry> entries) {
		this.entries = entries;
	}
}
