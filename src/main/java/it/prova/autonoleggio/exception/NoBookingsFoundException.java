package it.prova.autonoleggio.exception;

public class NoBookingsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoBookingsFoundException() {
		super("No bookings found for the user with this ID");
	}

}
