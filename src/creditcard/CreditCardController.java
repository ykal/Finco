package creditcard;

import banking.NotifyAssessor;
import banking.NotifyProperty;
import banking.models.BankingAccountFactory;
import creditcard.models.CreditCardAccountFactory;
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
import framework.models.customer.ICustomer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class CreditCardController extends Controller {

    CreditCard creditCard;
    CommandManager commandManager;

    public CreditCardController(CreditCard creditCard) {
        this.creditCard = creditCard;
        this.commandManager = creditCard.getCommandManager();
    }

    @Override
    protected void deposit(IEntry entry, Account account) {
        LoggableAction deposit = new Deposit(entry, account);
        System.out.println("Empty Reports :\n" + this.creditCard.getRepFile().toString());
        deposit = new Proxy(deposit, this.creditCard.getRepFile());
        IResult result = commandManager.submit(deposit);
        System.out.println("Deposite Report Added :\n" + this.creditCard.getRepFile().toString());
        this.creditCard.getAccFile().updateAccount(account);
        // Todo :: notify rule (if any)
    }

    @Override
    protected void withdraw(IEntry entry, Account account) {
        LoggableAction withdraw = new Withdraw(entry, account);
        withdraw = new Proxy(withdraw, this.creditCard.getRepFile());
        IResult result = commandManager.submit(withdraw);
        System.out.println("Withdraw Report Added :\n" + this.creditCard.getRepFile().toString());
        this.creditCard.getAccFile().updateAccount(account);
        // Todo :: notify rule
    }

    protected void report(){
        AbstractAction report = new Report(this.creditCard.getRepFile(), this.creditCard.getAccFile());
        IResult result = this.commandManager.submit(report);
                JDialogGenBill billFrm = new JDialogGenBill((String) result.getValue());
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    @Override
    protected void addInterest() {
        // Todo
    }

    @Override
    protected void addAccount(String ctype) {
        Account account = CreditCardAccountFactory.createAccount(this.creditCard.accountType, this.creditCard.ccnumber);
        Customer customer = this.creditCard.getCusFile().get((Customer c) -> c.getEmail().equals(this.creditCard.email));
        if (customer == null) {
            customer = CustomerFactory.createCustomer(ctype);
        }
        // TODO :: refactor them with constructor to take all properties
        customer.setEmail(this.creditCard.email);
        customer.setCity(this.creditCard.city);
        customer.setName(this.creditCard.clientName);
        customer.addAccount((IAccount) account);
        account.setOwner(customer);
        // Todo: add expire date to account
        this.creditCard.getAccFile().addAccount(account);
    }

    public void actionPerformed(ActionEvent event) {
        Object object = event.getSource();
        if (object == this.creditCard.JButton_NewCCAccount)
            JButtonNewCCAC_actionPerformed(event);
        else if (object == this.creditCard.JButton_GenBill)
            JButtonGenerateBill_actionPerformed(event);
        else if (object == this.creditCard.JButton_Deposit)
            JButtonDeposit_actionPerformed(event);
        else if (object == this.creditCard.JButton_Withdraw)
            JButtonWithdraw_actionPerformed(event);
    }

    void JButtonNewCCAC_actionPerformed(ActionEvent event) {
        /*
         * JDialog_AddPAcc type object is for adding personal information construct a
         * JDialog_AddPAcc type object set the boundaries and show it
         */

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(this.creditCard);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();
        // Todo :: add account logic
        addAccount(ICustomer.PERSON);

    }

    void JButtonGenerateBill_actionPerformed(ActionEvent event) {

        report();


    }

    void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = this.creditCard.getView().getTableSelection();
        if (selection >= 0) {
            String cc_number = (String) this.creditCard.getModel().getValueAt(selection, 1);
            String name = (String) this.creditCard.getModel().getValueAt(selection, 0);

            // Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(this.creditCard, name);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            IEntry entry = new Entry(this.creditCard.amountDeposit, LocalDate.now(), IEntry.DEPOSIT);
            Account account = this.creditCard.getAccFile().get((Account a) -> a.getId().equals(cc_number));
            deposit(entry, account);
        }
    }

    void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = this.creditCard.getView().getTableSelection();
        if (selection >= 0) {
            String cc_number = (String) this.creditCard.getModel().getValueAt(selection, 1);
            String name = (String) this.creditCard.getModel().getValueAt(selection, 0);

            // Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(this.creditCard, name);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            //  Todo :: Withdraw logic and compute new amount
            IEntry entry = new Entry(this.creditCard.amountDeposit, LocalDate.now(), IEntry.WITHDRAW);
            Account account = this.creditCard.getAccFile().get((Account a) -> a.getId().equals(cc_number));
            withdraw(entry, account);
        }

    }
}
