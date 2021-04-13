package framework.models.account;

import java.time.LocalDate;

public class Entry {
	private Float amount;
	private LocalDate date;

	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}