package banking;

import banking.models.BankingAccountFactory;
import banking.models.CheckingAccount;
import banking.models.CheckingAccountFactory;
import banking.persistance.BankAccFile;
import framework.FinCo;
import framework.controllers.CommandManager;
import framework.models.Data;
import framework.models.account.Account;
import framework.models.customer.Customer;
import framework.models.customer.Person;
import framework.observer.Observer;
import framework.persistence.ACCFile;
import framework.persistence.CUSFile;
import framework.persistence.REPFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class Bank implements Observer {

	String accountnr, clientName,street,city,zip,state,accountType,email;
	double amountDeposit;
	JButton JButton_PerAC = new JButton();
	JButton JButton_CompAC = new JButton();
	JButton JButton_Deposit = new JButton();
	JButton JButton_Withdraw = new JButton();
	JButton JButton_Addinterest= new JButton();
	Data model;
	FinCo app;
	BankController bankController;
	CommandManager commandManager;
	REPFile repFile;
	ACCFile accFile;
	CUSFile cusFile;

	public Bank() {
		commandManager = new CommandManager();
		bankController = new BankController(commandManager, this);
		repFile = new REPFile();
		accFile = new BankAccFile(this);
		cusFile = new CUSFile();
		model = new Data();
		populateModelColumns(model);
		app = new FinCo("Banking Application", model);
		addOperationButtons(app, model);
		accFile.attach(this);
	}

	public void populateModelColumns(Data model) {
		model.addColumn("AccountNr");
		model.addColumn("Email");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("P/C");
		model.addColumn("Ch/S");
		model.addColumn("Amount");
	}

	static public void main(String args[]){
		(new Bank()).app.start();
	}

	private void addOperationButtons(FinCo app, DefaultTableModel model) {
		// create operation buttons
		JButton_PerAC.setText("Add personal account");
		JButton_PerAC.setBounds(24,20,192,33);
		JButton_CompAC.setText("Add company account");
		JButton_CompAC.setActionCommand("jbutton");
		JButton_CompAC.setBounds(240,20,190,33);
		JButton_Deposit.setText("Deposit");
		JButton_Deposit.setBounds(468,104,96,33);
		JButton_Withdraw.setText("Withdraw");
		JButton_Addinterest.setBounds(448,20,106,33);
		JButton_Addinterest.setText("Add interest");
		JButton_Withdraw.setBounds(468,164,96,33);

		// add butons to FinCo
		app.addComponent(JButton_PerAC);
		app.addComponent(JButton_CompAC);
		app.addComponent(JButton_Deposit);
		app.addComponent(JButton_Withdraw);
		app.addComponent(JButton_Addinterest);

		// add action listener
		JButton_PerAC.addActionListener(bankController);
		JButton_CompAC.addActionListener(bankController);
		JButton_Deposit.addActionListener(bankController);
		JButton_Withdraw.addActionListener(bankController);
		JButton_Addinterest.addActionListener(bankController);
	}

	@Override
	public void update(Data data) {
		System.out.println("update is called \n" + data.toString());
		this.model = data;
		this.app.setTableModel(data);
	}
}
