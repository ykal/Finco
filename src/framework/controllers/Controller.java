package framework.controllers;

import framework.controllers.commands.*;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.Entry;
import framework.persistence.ACCFile;
import framework.persistence.CUSFile;
import framework.persistence.REPFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	private REPFile repFile;
	private ACCFile accFile;
	private CUSFile cusFile;
	public Controller(){}

	public Controller(REPFile repFile, ACCFile accFile, CUSFile cusFile) {
		this.repFile = repFile;
		this.accFile = accFile;
		this.cusFile = cusFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		TODO map this to deposit, addinterest, addaccount ... methods.
	}

	protected void report(){}

	protected void deposit(Entry entry, Account account) {
		LoggedAction deposit = new Deposit(entry, account);
		deposit = new Proxy(deposit);
		IResult result = deposit.execute();
	}

	protected void withdraw(Entry entry, Account account){
		LoggedAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw);
		IResult result = withdraw.execute();
	}

	protected void addInterest(){}

}