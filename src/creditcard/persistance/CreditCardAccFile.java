package creditcard.persistance;

import creditcard.CreditCard;
import framework.models.Data;
import framework.models.account.Account;
import framework.observer.Observer;
import framework.persistence.ACCFile;

public class CreditCardAccFile extends ACCFile {
    CreditCard app;

    public CreditCardAccFile(CreditCard app) {
        this.app = app;
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notify is called");
        Data data = new Data();
        app.populateModelColumns(data);
        accounts.forEach(account ->
                data.addRow(new Object[]{
                        account.getOwner().getName(),
                        account.getId(),
                        "-", // Todo :: get expire_date of account
                        account.getAccountType(),
                        account.getCurrentBalance()}));
        observers.forEach((Observer o) -> o.update(data));
    }


    @Override
    public void updateAccount(Account account) {
        removeAccount(account.getId());
        addAccount(account);
    }

}