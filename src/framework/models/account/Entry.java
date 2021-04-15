package framework.models.account;

import java.time.LocalDate;

public class Entry implements IEntry{
	private double amount;
	private LocalDate date;
	private String entryType;

	public Entry(double amount, LocalDate date, String entryType) {
		this.amount = amount;
		this.date = date;
		this.entryType = entryType;
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
	public String getEntryType() {
		return this.entryType ;
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