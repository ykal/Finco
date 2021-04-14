package framework.models.account;

import java.time.LocalDate;

public interface IEntry {
	double getAmount();
	void setAmount(double v);
	LocalDate getDate();
	void setDate(LocalDate date);
}
