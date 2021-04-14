package banking.models;

public class CheckingAccount extends BankingAccount {
	private double interest = 0.01;

	public CheckingAccount(String accnr) {
		setId(accnr);
	}

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