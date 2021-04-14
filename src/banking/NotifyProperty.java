package banking;

import framework.controllers.results.CurrentBalance;
import framework.controllers.results.IResult;
import framework.controllers.ruleengine.IProperty;
import framework.models.account.Entry;

public class NotifyProperty implements IProperty{
	private Entry entry;
	private IResult result;
	private String customerType;

	public NotifyProperty(Entry entry, IResult result, String customerType) {
		this.entry = entry;
		this.result = result;
		this.customerType = customerType;
	}

	@Override
	public Entry getEntry() {
		return entry;
	}

	@Override
	public IResult getResult() {
		return result;
	}

	@Override
	public String getCustomerType() {
		return customerType;
	}
}
