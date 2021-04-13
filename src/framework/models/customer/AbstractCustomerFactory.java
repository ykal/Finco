package framework.models.customer;

public class AbstractCustomerFactory {
	private static final AbstractCustomerFactory FACTORY_INSTANCE = new AbstractCustomerFactory();

	private AbstractCustomerFactory() {}

	public static AbstractCustomerFactory getObject() {
		return AbstractCustomerFactory.FACTORY_INSTANCE;
	}

	public static ICustomer createCustomer() {
		return null;
	}
}
