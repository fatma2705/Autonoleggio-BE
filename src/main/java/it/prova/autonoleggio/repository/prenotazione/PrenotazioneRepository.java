package it.prova.autonoleggio.repository.prenotazione;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.autonoleggio.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

	@Query("SELECT p FROM Prenotazione p WHERE p.utente.id = :utenteId and p.annullata = false")
	List<Prenotazione> findPrenotazioniByUtenteId(Long utenteId);

}
