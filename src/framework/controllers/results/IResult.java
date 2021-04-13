package framework.controllers.results;

public interface IResult<A> {
	String getMessage();
	A getValue();
}
