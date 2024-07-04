package it.prova.autonoleggio.exception;

public class AutoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AutoNotFoundException() {
		super("Auto not found");
	}
}
