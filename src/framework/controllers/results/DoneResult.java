package framework.controllers.results;

import framework.controllers.results.IResult;

public class DoneResult implements IResult {

	Object value;

	public DoneResult() {

	}
	public DoneResult(Object value) {
		this.value = value;
	}
	@Override
	public String getMessage() {
		return CurrentBalance.SUCCESS; // TODO Do something else!
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
