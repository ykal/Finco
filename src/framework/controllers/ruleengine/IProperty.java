package framework.controllers.ruleengine;

import framework.controllers.results.IResult;
import framework.models.account.Entry;
import framework.models.account.IEntry;

public interface IProperty {
	IEntry getEntry();
	IResult getResult();
	String getCustomerType();
}
