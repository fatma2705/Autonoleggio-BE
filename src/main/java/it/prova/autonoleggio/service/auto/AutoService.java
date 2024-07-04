package it.prova.autonoleggio.service.auto;

import java.util.List;

import it.prova.autonoleggio.model.Auto;

public interface AutoService {

	public List<Auto> listAllAuto();

	public Auto caricaSingolaAuto(Long id);

	public Auto aggiorna(Auto autoInstance);

	public void inserisciNuovo(Auto autoInstance);

	public void rimuovi(Long idToRemove);
	
	public List<Auto> findByExample(Auto example);

}
