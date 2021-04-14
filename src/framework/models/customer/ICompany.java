package framework.models.customer;

public interface ICompany extends ICustomer{
	public default String getCustomerType() {
		return "Company";
	}
}
