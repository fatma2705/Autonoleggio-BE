package it.prova.autonoleggio.service.auto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import it.prova.autonoleggio.model.Auto;

public interface AutoService {

	public List<Auto> listAllAuto();

	public Auto caricaSingolaAuto(Long id);

	public Auto aggiorna(Auto autoInstance);

	public Auto inserisciNuovo(Auto autoInstance);

	public void rimuovi(Long idToRemove);
	
	public List<Auto> findByExample(Auto example);
	
	public Optional<Auto> findByTarga(String targa);
	
    boolean isAutoAvailable(Long autoId, LocalDate dataInizio, LocalDate dataFine);
    
    List<Auto> findAvailableAutos(LocalDate dataInizio, LocalDate dataFine);


}
