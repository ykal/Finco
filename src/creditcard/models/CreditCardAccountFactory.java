package creditcard.models;

import banking.models.BankingAccount;
import banking.models.CheckingAccount;
import banking.models.SavingAccount;

public class CreditCardAccountFactory {
    public static CreditCardAccount createAccount(String accountType, String id) {
        if (accountType.equals(CreditCardAccount.GOLD)) return new Gold(id);
        if(accountType.equals(CreditCardAccount.SILIVER)) return new Silver(id);
        if(accountType.equals(CreditCardAccount.BRONZE)) return new Bronze(id);
        return null;
    }
}
