package banking;

import banking.persistance.BankAccFile;
import framework.FinCo;
import framework.View;
import framework.controllers.CommandManager;
import framework.models.Data;
import framework.observer.Observer;
import framework.persistence.ACCFile;
import framework.persistence.CUSFile;
import framework.persistence.REPFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Bank extends FinCo {

	String accountnr, clientName,street,city,zip,state,accountType,email;
	double amountDeposit;
	JButton JButton_PerAC = new JButton();
	JButton JButton_CompAC = new JButton();
	JButton JButton_Deposit = new JButton();
	JButton JButton_Withdraw = new JButton();
	JButton JButton_Addinterest= new JButton();


	public Bank(String title, Data model) {
		super(title, model);
		this.setAccFile(new BankAccFile(this));
		this.setCommandManager(new CommandManager());
		this.setController(new BankController(this));
		this.setModel(new Data());
		populateModelColumns(this.getModel());
		addOperationButtons(this.getView(), model);
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
		(new Bank("Bank Application", new Data())).getView().start();
	}

	private void addOperationButtons(View app, DefaultTableModel model) {
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
		JButton_PerAC.addActionListener(getController());
		JButton_CompAC.addActionListener(getController());
		JButton_Deposit.addActionListener(getController());
		JButton_Withdraw.addActionListener(getController());
		JButton_Addinterest.addActionListener(getController());
	}

	@Override
	public void update(Data data) {
		System.out.println("update is called \n" + data.toString());
		this.setModel(data);
		this.getView().setTableModel(data);
	}
}
