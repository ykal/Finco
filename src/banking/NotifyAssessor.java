package banking;

import framework.controllers.results.CurrentBalance;
import framework.controllers.ruleengine.AbstractAssessor;
import framework.controllers.ruleengine.IProperty;
import framework.models.customer.ICustomer;

public class NotifyAssessor extends AbstractAssessor<IProperty> {
	private IProperty property;

	public NotifyAssessor(IProperty property) {
		this.property = property;
	}

	@Override
	public boolean evaluate() {
		if (property.getCustomerType() == ICustomer.COMPANY ||
				property.getEntry().getAmount() >= 400 ||
				property.getResult().getMessage().equals(CurrentBalance.NEGATIVE_BALANCE))
			return true;
		return false;
	}
}
