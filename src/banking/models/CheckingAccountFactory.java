package banking.models;

public class CheckingAccountFactory extends BankingAccountFactory{
	@Override
	public BankingAccount createAccount(String id) {
		return new CheckingAccount(id);
	}
}
