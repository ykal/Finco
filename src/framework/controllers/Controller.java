package framework.controllers;

import framework.controllers.commands.*;
import framework.controllers.results.IResult;
import framework.models.account.Account;
import framework.models.account.Entry;
import framework.models.account.IEntry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
//		TODO map this to deposit, addinterest, addaccount ... methods.
	}

	protected void report(){
//		TODO
	}

	protected abstract void deposit(IEntry entry, Account account);

	protected abstract void withdraw(IEntry entry, Account account);

	protected void addInterest(){
//		TODO
	}

	protected void addAccount() {
//		TODO
	}

}
