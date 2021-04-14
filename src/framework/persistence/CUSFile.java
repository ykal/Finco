package framework.persistence;

import framework.models.customer.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CUSFile {
	List<Customer> customers;

	public CUSFile(){
		this.customers = new ArrayList<>();
	}

	public void add(Customer customer) {
		this.customers.add(customer);
	}

	public Customer get(Predicate<Customer> predicate) {
		for (Customer c : customers) {
			if (predicate.test(c))
				return c;
		}
		return null;
	}
}
