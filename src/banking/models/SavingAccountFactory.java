package banking.models;

public class SavingAccountFactory extends BankingAccountFactory{
	@Override
	public BankingAccount createAccount(String id) {
		return new SavingAccount(id);
	}
}
