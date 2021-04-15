package framework.models.account;

import java.time.LocalDate;

public interface IEntry {
	public static final String WITHDRAW = "WITHDRAW";
	public static final String DEPOSIT = "DEPOSIT";
	double getAmount();
	void setAmount(double v);
	String getEntryType();
	LocalDate getDate();
	void setDate(LocalDate date);
}
