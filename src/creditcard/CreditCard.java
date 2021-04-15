package creditcard;

import banking.persistance.BankAccFile;
import creditcard.persistance.CreditCardAccFile;
import framework.FinCo;
import framework.View;
import framework.controllers.CommandManager;
import framework.models.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CreditCard extends FinCo {

    String clientName, street, city, zip, state, accountType, amountDeposit, expdate, ccnumber, email;

    JButton JButton_NewCCAccount = new JButton();
    JButton JButton_GenBill = new JButton();
    JButton JButton_Deposit = new JButton();
    JButton JButton_Withdraw = new JButton();

    public CreditCard(String title, Data model) {
        super(title, model);
        this.setController(new CreditCardController(this));
        this.setAccFile(new CreditCardAccFile(this));
        this.setCommandManager(new CommandManager());
        this.setModel(new Data());
        populateModelColumns(this.getModel());
        addOperationButtons(this.getView(), this.getModel());
    }

    static public void main(String args[]){
        (new CreditCard("Credit Card Application", new Data())).getView().start();
    }


    public void populateModelColumns(Data model) {
        model.addColumn("Name");
        model.addColumn("CC Number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
    }

    private void addOperationButtons(View view, DefaultTableModel model) {
        JButton_NewCCAccount.setText("Add Credit-card account");
        this.getView().addComponent(JButton_NewCCAccount);
        JButton_NewCCAccount.setBounds(24, 20, 192, 33);
        JButton_GenBill.setText("Generate Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        this.getView().addComponent(JButton_GenBill);
        JButton_GenBill.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        this.getView().addComponent(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Charge");
        this.getView().addComponent(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_GenBill.setActionCommand("jbutton");


        JButton_NewCCAccount.addActionListener(this.getController());
        JButton_GenBill.addActionListener(this.getController());
        JButton_Deposit.addActionListener(this.getController());
        JButton_Withdraw.addActionListener(this.getController());
    }
}
