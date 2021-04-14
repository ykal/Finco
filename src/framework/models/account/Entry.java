package framework.models.account;

import java.time.LocalDate;

public class Entry implements IEntry{
	private double amount;
	private LocalDate date;

	public Entry(double amount, LocalDate date) {
		this.amount = amount;
		this.date = date;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

}