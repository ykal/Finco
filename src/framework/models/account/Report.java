package framework.models.account;

import framework.models.account.Account;
import framework.models.account.Entry;

import java.time.LocalDate;

public class Report {
	private double amount;
	private double newBalance;
	private String accId;
	private String entryType;
	private LocalDate date;

	public Report(double amount, double newBalance,  String accId, LocalDate date,String entryType) {
		this.amount = amount;
		this.newBalance = newBalance;
		this.accId = accId;
		this.date = date;
		this.entryType = entryType;
	}

	public double getAmount() {
		return amount;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public String getEntryType () {return entryType;}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Report{" +
				"amount=" + amount +
				"newBalance=" + newBalance +
				"type=" + entryType +
				", accId='" + accId + '\'' +
				", date=" + date +
				"}\n";
	}
}
