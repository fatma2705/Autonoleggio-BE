package it.prova.autonoleggio.service.prenotazione;

import java.util.List;

import it.prova.autonoleggio.model.Prenotazione;

public interface PrenotazioneService {

	public void annullaPrenotazione(Prenotazione prenotazione);

	public List<Prenotazione> listAllPrenotazioni();

	public Prenotazione modificaPrenotazione(Prenotazione prenotazione);

	public Prenotazione eliminaPrenotazione(Long id);

	public List<Prenotazione> listaStoricoPrenotazioni();

	public Prenotazione insertPrenotazione(Prenotazione prenotazione);

}
