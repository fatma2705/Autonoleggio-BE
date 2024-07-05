package it.prova.autonoleggio.exception;

public class CarNotAvailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CarNotAvailableException() {
		 super("The car is not available for this date.");
	}
}


