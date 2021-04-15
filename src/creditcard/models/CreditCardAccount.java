package creditcard.models;

import framework.models.account.Account;

public abstract class CreditCardAccount extends Account {
    public static final String GOLD  = "Gold";
    public static final String SILIVER = "Silver";
    public static final String BRONZE = "Bronze";

    private String cc_number;
    private String exp_date;
    private double last_month_balance;

    public String getCc_number(){return cc_number;}

    @Override
    public String getId() {
        return getCc_number();
    }

    @Override
    public void setId(String cc_number) {
        this.cc_number = cc_number;
        super.setId(cc_number);
    }

    public void setCc_number(String cc_number) {
        setId(cc_number);
    }
}
