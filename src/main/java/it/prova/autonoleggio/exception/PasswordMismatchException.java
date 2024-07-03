package it.prova.autonoleggio.exception;

public class PasswordMismatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordMismatchException(String msg) {
		super(msg);
	}
}
