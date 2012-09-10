package guestbook;

public class CalculatorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CalculatorException(String reason) {
		super(reason);
	}
	
}
