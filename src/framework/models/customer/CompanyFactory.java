package framework.models.customer;

import framework.models.customer.*;

public class CompanyFactory {
	public static ICustomer createCustomer() {
		return new Company();
	}
}
