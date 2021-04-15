package creditcard.models;

import creditcard.CreditCard;

public class Silver extends CreditCardAccount{

    private double interest = 0.18;
    private double minimumPayment = 0.14;


    Silver(String cc_num) {
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
        return CreditCardAccount.SILIVER;
    }
    @Override
    public double getMp() {
        return minimumPayment;
    }
}
