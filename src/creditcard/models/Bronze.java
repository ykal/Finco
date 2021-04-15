package creditcard.models;

import creditcard.CreditCard;

public class Bronze extends CreditCardAccount{

    private double interest = 0.20;
    private double minimumPayment = 0.22;


    Bronze(String cc_num) {
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
        return CreditCardAccount.BRONZE;
    }

    @Override
    public double getMp() {
        return minimumPayment;
    }
}
