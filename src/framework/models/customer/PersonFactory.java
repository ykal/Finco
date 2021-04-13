package framework.models.customer;

import framework.models.customer.*;

public class PersonFactory {
	public static ICustomer createCustomer() {
		return new Person();
	}
}
