package framework.controllers.results;

public class CurrentBalance implements IResult<double> {
	public static final String NEGATIVE_BALANCE = "negative_balance";
	public static final String SUCCESS = "success";
	private double value;
	private String message;

	public CurrentBalance(double value, String message) {
		this.value = value;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public double getValue() {
		return value;
	}
}