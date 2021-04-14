package banking.models;

import framework.models.account.Account;

public abstract class BankingAccount extends Account {
	public static final String TYPE_SAVING  = "Saving";
	public static final String TYPE_CHECKING = "Checking";

	private String accnr;
	public String getAccnr(){return accnr;}

	@Override
	public String getId() {
		return getAccnr();
	}

	@Override
	public void setId(String accnr) {
		this.accnr = accnr;
		super.setId(accnr);
	}

	public void setAccnr(String accnr) {
		setId(accnr);
	}
}