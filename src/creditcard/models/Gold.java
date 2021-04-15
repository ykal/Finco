package creditcard.models;

import creditcard.CreditCard;

public class Gold extends CreditCardAccount{

    private double interest = 0.14;
    private double minimumPayment = 0.12;


    Gold(String cc_num) {
        setId(cc_num);
    }

    public void addInterest() {
        double currentBalance = super.getCurrentBalance();
        double newBalance = currentBalance + (currentBalance*interest);
        super.setCurrentBalance(newBalance);
    }

    @Override
    public double getInterest() {
        return interest;
    }

    @Override
    public String getAccountType() {
        return CreditCardAccount.GOLD;
    }
}
