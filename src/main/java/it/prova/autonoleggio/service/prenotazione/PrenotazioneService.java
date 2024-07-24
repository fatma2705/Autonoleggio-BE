package it.prova.autonoleggio.service.prenotazione;

import java.util.List;

import it.prova.autonoleggio.model.Prenotazione;

public interface PrenotazioneService {

	public void annullaPrenotazione(Long id,String username);

	public Prenotazione modificaPrenotazione(Prenotazione prenotazione,String username);

	public void eliminaPrenotazione(Long id,String username);

	public List<Prenotazione> listaStoricoPrenotazioniAdmin(String Admin);
	
	public List<Prenotazione> listaStoricoPrenotazioniUtente(String username);

	public Prenotazione insertPrenotazione(Prenotazione prenotazione,String username);
	
	public Prenotazione caricaSingolaPrenotazione(Long id);

}
