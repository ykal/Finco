package framework.models.account;

import java.time.LocalDate;

public class Entry implements IEntry{
	private double amount;
	private LocalDate date;

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}