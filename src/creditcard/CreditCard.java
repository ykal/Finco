package creditcard;

import banking.persistance.BankAccFile;
import framework.FinCo;
import framework.controllers.CommandManager;
import framework.models.Data;

public class CreditCard extends FinCo {
    public CreditCard(String title, Data model) {
        super(title, model);
        this.setAccFile(new BankAccFile(this));
        this.setCommandManager(new CommandManager());
        this.setModel(new Data());
        populateModelColumns(this.getModel());
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
}
