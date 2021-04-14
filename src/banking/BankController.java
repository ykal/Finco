package banking;

import banking.models.*;
import framework.controllers.CommandManager;
import framework.controllers.Controller;
import framework.controllers.commands.*;
import framework.controllers.commands.AbstractAction;
import framework.controllers.results.IResult;
import framework.controllers.ruleengine.AbstractAssessor;
import framework.controllers.ruleengine.IProperty;
import framework.controllers.ruleengine.Rule;
import framework.models.account.Account;
import framework.models.account.Entry;
import framework.models.account.IAccount;
import framework.models.account.IEntry;
import framework.models.customer.CompanyFactory;
import framework.models.customer.Customer;
import framework.models.customer.ICustomer;
import framework.models.customer.PersonFactory;
import framework.persistence.CUSFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class BankController extends Controller {
	private CommandManager commandManager;
	private Bank view;

	public BankController(CommandManager commandManager, Bank view) {
		this.commandManager = commandManager;
		this.view = view;
	}

	public static BankingAccountFactory getFactory(String accountType) {
		return accountType.equals(BankingAccount.TYPE_CHECKING) ? new CheckingAccountFactory() : new SavingAccountFactory();
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
	protected void deposit(IEntry entry, Account account){
		LoggableAction deposit = new Deposit(entry, account);
		System.out.println("Empty Reports :\n" + view.repFile.toString());
		deposit = new Proxy(deposit, view.repFile);
		IResult result = commandManager.submit(deposit);
		System.out.println("One Report Added :\n" + view.repFile.toString());
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void withdraw(IEntry entry, Account account){
		LoggableAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw, view.repFile);
		IResult result = commandManager.submit(withdraw);
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void addAccount(String ctype) {
		BankingAccountFactory bankingAccountFactory = getFactory(view.accountType);
		Account account = bankingAccountFactory.createAccount(view.accountnr);
		Customer customer = view.cusFile.get((Customer c) -> c.getEmail().equals(view.email));
		if (customer == null) {
			customer = ctype.equals(Customer.PERSON) ?
					PersonFactory.createCustomer() :
					CompanyFactory.createCustomer();
		}
		// TODO :: refactor them with constructor to take all properties
		customer.setEmail(view.email);
		customer.setCity(view.city);
		customer.setName(view.clientName);
		customer.addAccount((IAccount) account);
		account.setOwner(customer);
		view.accFile.addAccount(account);
	}

	@Override
	protected void addInterest() {
		AbstractAction action = new AddInterest(view.accFile);
		IResult result = commandManager.submit(action);
	}

	private void runNotifyRule(IEntry entry, Account account, IResult result) {
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

		addAccount(Customer.PERSON);
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

		addAccount(Customer.COMPANY);
	}

	void JButtonDeposit_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.view.app.getTableSelection();
		if (selection >= 0){
			String accnr = (String)this.view.model.getValueAt(selection, 0);

			//Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(view,accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			IEntry entry = new Entry(view.amountDeposit, LocalDate.now());
			Account account = view.accFile.get((Account a) -> a.getId().equals(accnr));
			deposit(entry, account);
		}


	}

	void JButtonWithdraw_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.view.app.getTableSelection();
		if (selection >=0) {
			String accnr = (String)this.view.model.getValueAt(selection, 0);

			//Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(this.view,accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			IEntry entry = new Entry(view.amountDeposit, LocalDate.now());
			Account account = view.accFile.get((Account a) -> a.getId().equals(accnr));
			withdraw(entry, account);
		}


	}

	void JButtonAddinterest_actionPerformed(ActionEvent event)
	{
		JOptionPane.showMessageDialog(this.view.JButton_Addinterest,
				"Add interest to all accounts",
				"Add interest to all accounts",
				JOptionPane.WARNING_MESSAGE);
		addInterest();
	}
}
