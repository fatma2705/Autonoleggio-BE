package it.prova.autonoleggio.exception;

public class IdNotNullForInsertException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdNotNullForInsertException() {
		super("Id must be null for insert operation");
	}
}