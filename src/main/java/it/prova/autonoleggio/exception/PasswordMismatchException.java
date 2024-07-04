package it.prova.autonoleggio.exception;

public class PasswordMismatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordMismatchException() {
		super("Passwords do not match. Please ensure both password fields are identical");
	}
}
