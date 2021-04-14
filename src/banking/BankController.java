package banking;

import framework.controllers.CommandManager;
import framework.controllers.Controller;
import framework.controllers.commands.*;
import framework.controllers.results.IResult;
import framework.controllers.ruleengine.AbstractAssessor;
import framework.controllers.ruleengine.IProperty;
import framework.controllers.ruleengine.Rule;
import framework.models.account.Account;
import framework.models.account.Entry;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BankController extends Controller {
	private CommandManager commandManager;
	private Bank view;

	public BankController(CommandManager commandManager, Bank view) {
		this.commandManager = commandManager;
		this.view = view;
	}

	public void actionPerformed(ActionEvent event)
	{
		Object object = event.getSource();
		if (object == view.JButton_PerAC)
			JButtonPerAC_actionPerformed(event);
		else if (object == view.JButton_CompAC)
			JButtonCompAC_actionPerformed(event);
		else if (object == view.JButton_Deposit)
			JButtonDeposit_actionPerformed(event);
		else if (object == view.JButton_Withdraw)
			JButtonWithdraw_actionPerformed(event);
		else if (object == view.JButton_Addinterest)
			JButtonAddinterest_actionPerformed(event);

	}

	@Override
	protected void deposit(Entry entry, Account account){
		LoggableAction deposit = new Deposit(entry, account);
		deposit = new Proxy(deposit);
		IResult result = commandManager.submit(deposit);
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void withdraw(Entry entry, Account account){
		LoggableAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw);
		IResult result = commandManager.submit(withdraw);
		runNotifyRule(entry, account, result);
	}

	private void runNotifyRule(Entry entry, Account account, IResult result) {
		IProperty property = new NotifyProperty(entry, result,
				account.getOwner().getCustomerType());
		AbstractAssessor<IProperty> assessor = new NotifyAssessor(property);
		Rule notifyRule = new Rule(assessor, new ENotify(), commandManager);
		notifyRule.applyRule();
	}

	void JButtonPerAC_actionPerformed(ActionEvent event)
	{
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

		JDialog_AddPAcc pac = new JDialog_AddPAcc(view);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		// Todo:: call controller.addAccount
	}

	void JButtonCompAC_actionPerformed(ActionEvent event)
	{
		/*
		 construct a JDialog_AddCompAcc type object
		 set the boundaries and
		 show it
		*/

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(view);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		// Todo :: call controller.addCompanyAccount

	}

	void JButtonDeposit_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.view.app.getTableSelection();
		if (selection >=0){
			String accnr = (String)this.view.model.getValueAt(selection, 0);

			//Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(view,accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			// TODO :: call controller.deposit
			// this.deposit(entry, account);
		}


	}

	void JButtonWithdraw_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.view.app.getTableSelection();
		if (selection >=0){
			String accnr = (String)this.view.model.getValueAt(selection, 0);

			//Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(this.view,accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			// TODO :: call controller.withdraw
			//this.withdraw(entry, account);
		}


	}

	void JButtonAddinterest_actionPerformed(ActionEvent event)
	{
		JOptionPane.showMessageDialog(this.view.JButton_Addinterest, "Add interest to all accounts","Add interest to all accounts",JOptionPane.WARNING_MESSAGE);

	}
}
