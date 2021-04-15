package framework.models.account;

public interface IAccount {
    void addInterest();
    double getInterest();
    String getAccountType();
    default double getMp() {return  0D;}
}
