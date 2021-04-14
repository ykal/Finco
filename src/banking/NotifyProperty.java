package banking;

import framework.controllers.results.IResult;
import framework.controllers.ruleengine.IProperty;
import framework.models.account.Entry;
import framework.models.account.IEntry;

public class NotifyProperty implements IProperty{
	private IEntry entry;
	private IResult result;
	private String customerType;

	public NotifyProperty(IEntry entry, IResult result, String customerType) {
		this.entry = entry;
		this.result = result;
		this.customerType = customerType;
	}

	@Override
	public IEntry getEntry() {
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
