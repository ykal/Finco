package framework.controllers.results;

import framework.controllers.results.IResult;

public class DoneResult implements IResult {
	@Override
	public String getMessage() {
		return CurrentBalance.SUCCESS; // TODO Do something else!
	}

	@Override
	public Object getValue() {
		return null;
	}
}
