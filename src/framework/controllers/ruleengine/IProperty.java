package framework.controllers.ruleengine;

import framework.controllers.results.IResult;
import framework.models.account.Entry;

public interface IProperty {
	Entry getEntry();
	IResult getResult();
	String getCustomerType();
}
