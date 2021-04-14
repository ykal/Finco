package banking.models;

public class SavingAccount extends BankingAccount {
	private double interest = 0.0325;

	public void addInterest() {
		double currentBalance = super.getCurrentBalance();
		double newBalance = currentBalance + (currentBalance*interest);
		super.setCurrentBalance(newBalance);
	}

	@Override
	public double getInterest() {
		return interest;
	}
}