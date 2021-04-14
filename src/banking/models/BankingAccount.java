package banking.models;

import framework.models.account.Account;

public abstract class BankingAccount extends Account {
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