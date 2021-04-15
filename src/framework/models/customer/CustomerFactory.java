package framework.models.customer;

public class CustomerFactory {

	public static Customer createCustomer(String customerType) {
		if(customerType.equals(ICustomer.COMPANY)) return new Company();
		if(customerType.equals(ICustomer.PERSON)) return new Person();
		return  null; // default
	}
}
