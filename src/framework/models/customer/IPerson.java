package framework.models.customer;

public interface IPerson extends ICustomer{
	public default String getCustomerType(){
		return ICustomer.PERSON;
	}
}
