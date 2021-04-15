package banking.models;

public abstract class BankingAccountFactory {
	public static BankingAccount createAccount(String accountType, String id) {
		if (accountType.equals(BankingAccount.TYPE_CHECKING)) return new CheckingAccount(id);
		if(accountType.equals(BankingAccount.TYPE_SAVING)) return new SavingAccount(id);
		return null;
	}
}
