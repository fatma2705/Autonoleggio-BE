package it.prova.autonoleggio.exception;

public class PrenotazioneNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PrenotazioneNotfoundException() {
		super("Prenotazione not found");
	}
}
