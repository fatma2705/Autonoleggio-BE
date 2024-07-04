package it.prova.autonoleggio.exception;

public class CreditMustBeZeroException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CreditMustBeZeroException() {
		super("Credit must be 0");
	}
}
