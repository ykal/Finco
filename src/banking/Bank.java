package banking;

import framework.FinCo;
import framework.models.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bank {

	String accountnr, clientName,street,city,zip,state,accountType,clientType,amountDeposit;
	JButton JButton_PerAC = new JButton();
	JButton JButton_CompAC = new JButton();
	JButton JButton_Deposit = new JButton();
	JButton JButton_Withdraw = new JButton();
	JButton JButton_Addinterest= new JButton();
	Data model;
	FinCo app;

	public Bank() {
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
		app.addComponent(JButton_PerAC);
		app.addComponent(JButton_CompAC);
		app.addComponent(JButton_Deposit);
		app.addComponent(JButton_Withdraw);
		app.addComponent(JButton_Addinterest);
		SymAction lSymAction = new SymAction();
		JButton_PerAC.addActionListener(lSymAction);
		JButton_CompAC.addActionListener(lSymAction);
		JButton_Deposit.addActionListener(lSymAction);
		JButton_Withdraw.addActionListener(lSymAction);
		JButton_Addinterest.addActionListener(lSymAction);
	}

	private static void createAddPersonalAccButtn(FinCo app, Data model) {
		JButton JButton_PerAC = new JButton("Add personal account");
		JButton_PerAC.setBounds(24,20,192,33);
		JButton_PerAC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[]{"Test", 23414});
				System.out.println(app.getTableSelection());
			}
		});
		app.addComponent(JButton_PerAC);
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object object = event.getSource();
			if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_CompAC)
				JButtonCompAC_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);
			else if (object == JButton_Addinterest)
				JButtonAddinterest_actionPerformed(event);

		}
	}

	void JButtonPerAC_actionPerformed(ActionEvent event)
	{
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

		JDialog_AddPAcc pac = new JDialog_AddPAcc(this);
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

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		// Todo :: call controller.addCompanyAccount

	}

	void JButtonDeposit_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.app.getTableSelection();
		if (selection >=0){
			String accnr = (String)this.model.getValueAt(selection, 0);

			//Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(this,accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			// TODO :: call controller.deposit
		}


	}

	void JButtonWithdraw_actionPerformed(ActionEvent event)
	{
		// get selected name
		int selection = this.app.getTableSelection();
		if (selection >=0){
			String accnr = (String)model.getValueAt(selection, 0);

			//Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(this,accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			// TODO :: call controller.withdraw
		}


	}

	void JButtonAddinterest_actionPerformed(ActionEvent event)
	{
		JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts","Add interest to all accounts",JOptionPane.WARNING_MESSAGE);

	}
}
