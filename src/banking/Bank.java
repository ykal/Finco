package banking;

import framework.FinCo;
import framework.controllers.CommandManager;
import framework.models.Data;

import javax.swing.*;

public class Bank {

	String accountnr, clientName,street,city,zip,state,accountType,clientType,amountDeposit;
	JButton JButton_PerAC = new JButton();
	JButton JButton_CompAC = new JButton();
	JButton JButton_Deposit = new JButton();
	JButton JButton_Withdraw = new JButton();
	JButton JButton_Addinterest= new JButton();
	Data model;
	FinCo app;
	BankController bankController;
	CommandManager commandManager;

	public Bank() {
		bankController = new BankController(commandManager, this);
		model = new Data();
		model.addColumn("First");
		model.addColumn("Second");
		app = new FinCo("Banking Application", model);
		addOperationButtons(app, model);
	}

	static public void main(String args[]){
		(new Bank()).app.start();
	}

	private void addOperationButtons(FinCo app, Data model) {
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
}
