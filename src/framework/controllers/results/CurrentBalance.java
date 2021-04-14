package framework.controllers.results;

public class CurrentBalance implements IResult<Double> {
	private String message;
	private Double value;

	public CurrentBalance(String message, Double value) {
		this.message = message;
		this.value = value;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Double getValue() {
		return value;
	}
}