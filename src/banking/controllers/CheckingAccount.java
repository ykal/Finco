package banking.controllers;

public class CheckingAccount extends BankingAccount {
	private Double interest = 0.01;

	@Override
	public void addInterest() {
		Double currentBalance = super.getCurrentBalance();
		Double newBalance = currentBalance + (currentBalance*interest);
		super.setCurrentBalance(newBalance);
	}

	@Override
	public Double getInterest() {
		return interest;
	}
}
