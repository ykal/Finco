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
import framework.models.customer.Customer;
import framework.models.customer.CustomerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class BankController extends Controller {
	private CommandManager commandManager;
	private Bank bank;

	public BankController(Bank bank) {
		this.commandManager = bank.getCommandManager();
		this.bank = bank;
	}

	public void actionPerformed(ActionEvent event)
	{
		Object object = event.getSource();
		if (object == this.bank.JButton_PerAC)
			JButtonPerAC_actionPerformed(event);
		else if (object ==  this.bank.JButton_CompAC)
			JButtonCompAC_actionPerformed(event);
		else if (object ==  this.bank.JButton_Deposit)
			JButtonDeposit_actionPerformed(event);
		else if (object ==  this.bank.JButton_Withdraw)
			JButtonWithdraw_actionPerformed(event);
		else if (object ==  this.bank.JButton_Addinterest)
			JButtonAddinterest_actionPerformed(event);

	}

	@Override
	protected void deposit(IEntry entry, Account account){
		LoggableAction deposit = new Deposit(entry, account);
		System.out.println("Empty Reports :\n" + this.bank.getRepFile().toString());
		deposit = new Proxy(deposit, this.bank.getRepFile());
		IResult result = commandManager.submit(deposit);
		System.out.println("Deposite Report Added :\n" + this.bank.getRepFile().toString());
		this.bank.getAccFile().updateAccount(account);
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void withdraw(IEntry entry, Account account){
		LoggableAction withdraw = new Withdraw(entry, account);
		withdraw = new Proxy(withdraw, this.bank.getRepFile());
		IResult result = commandManager.submit(withdraw);
		System.out.println("Withdraw Report Added :\n" + this.bank.getRepFile().toString());
		this.bank.getAccFile().updateAccount(account);
		runNotifyRule(entry, account, result);
	}

	@Override
	protected void addAccount(String ctype) {
		Account account = BankingAccountFactory.createAccount(this.bank.accountType, this.bank.accountnr);
		Customer customer = this.bank.getCusFile().get((Customer c) -> c.getEmail().equals(this.bank.email));
		if (customer == null) {
			customer = CustomerFactory.createCustomer(ctype);
		}
		// TODO :: refactor them with constructor to take all properties
		customer.setEmail(this.bank.email);
		customer.setCity(this.bank.city);
		customer.setName(this.bank.clientName);
		customer.addAccount((IAccount) account);
		account.setOwner(customer);
		this.bank.getAccFile().addAccount(account);
	}

	@Override
	protected void addInterest() {
		AbstractAction action = new AddInterest(this.bank.getAccFile());
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

		JDialog_AddPAcc pac = new JDialog_AddPAcc(this.bank);
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

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this.bank);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		addAccount(Customer.COMPANY);
	}

	void JButtonDeposit_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.bank.getView().getTableSelection();
		if (selection >= 0){
			String accnr = (String)this.bank.getModel().getValueAt(selection, 0);

			//Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(this.bank,accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			IEntry entry = new Entry(this.bank.amountDeposit, LocalDate.now(), IEntry.DEPOSIT);
			Account account = this.bank.getAccFile().get((Account a) -> a.getId().equals(accnr));
			deposit(entry, account);
		}


	}

	void JButtonWithdraw_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.bank.getView().getTableSelection();
		if (selection >=0) {
			String accnr = (String)this.bank.getModel().getValueAt(selection, 0);

			//Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(this.bank,accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			IEntry entry = new Entry(this.bank.amountDeposit, LocalDate.now(), IEntry.WITHDRAW);
			Account account = this.bank.getAccFile().get((Account a) -> a.getId().equals(accnr));
			withdraw(entry, account);
		}


	}

	void JButtonAddinterest_actionPerformed(ActionEvent event)
	{
		JOptionPane.showMessageDialog(this.bank.JButton_Addinterest,
				"Add interest to all accounts",
				"Add interest to all accounts",
				JOptionPane.WARNING_MESSAGE);
		addInterest();
	}
}
