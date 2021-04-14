package framework.models.account;

import framework.models.account.Account;
import framework.models.account.Entry;

import java.time.LocalDate;

public class Report {
	private double amount;
	private String accId;
	private LocalDate date;

	public Report(double amount, String accId, LocalDate date) {
		this.amount = amount;
		this.accId = accId;
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

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
				", accId='" + accId + '\'' +
				", date=" + date +
				'}';
	}
}
