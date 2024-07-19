package it.prova.autonoleggio.repository.auto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.model.TipoAuto;
import it.prova.autonoleggio.model.TipoMotore;

public interface AutoRepository extends CrudRepository<Auto, Long> {

	@Query("SELECT a FROM Auto a WHERE " + "(:targa IS NULL OR a.targa = :targa) "
			+ "AND (:tipologia IS NULL OR a.tipologia = :tipologia) " + "AND (:marca IS NULL OR a.marca = :marca) "
			+ "AND (:modello IS NULL OR a.modello = :modello) "
			+ "AND (:cilindrata IS NULL OR a.cilindrata = :cilindrata) "
			+ "AND (:tipoMotore IS NULL OR a.tipoMotore = :tipoMotore) "
			+ "AND (:numeroPosti IS NULL OR a.numeroPosti = :numeroPosti) "
			+ "AND (:cambioAutomatico IS NULL OR a.cambioAutomatico = :cambioAutomatico) "
			+ "AND (:dataImmatricolazione IS NULL OR a.dataImmatricolazione = :dataImmatricolazione) "
			+ "AND (:descrizione IS NULL OR a.descrizione = :descrizione) "
			+ "AND (:prezzoPerGiornata IS NULL OR a.prezzoPerGiornata = :prezzoPerGiornata)")
	List<Auto> findByExample(@Param("targa") String targa, @Param("tipologia") TipoAuto tipologia,
			@Param("marca") String marca, @Param("modello") String modello, @Param("cilindrata") Integer cilindrata,
			@Param("tipoMotore") TipoMotore tipoMotore, @Param("numeroPosti") Integer numeroPosti,
			@Param("cambioAutomatico") Boolean cambioAutomatico,
			@Param("dataImmatricolazione") LocalDate dataImmatricolazione, @Param("descrizione") String descrizione,
			@Param("prezzoPerGiornata") Float prezzoPerGiornata);

	@Query("SELECT COUNT(*)  " + "FROM Prenotazione p " + "WHERE p.auto.id = :autoId " + "AND p.annullata = false "
			+ "AND p.dataInizio <= :dataFine " + "AND p.dataFine >= :dataInizio")
	Integer countPrenotazioniNonSovrapposte(@Param("autoId") Long autoId, @Param("dataInizio") LocalDate dataInizio,
			@Param("dataFine") LocalDate dataFine);

	default boolean isAutoAvailable(Long autoId, LocalDate dataInizio, LocalDate dataFine) {
		Integer count = countPrenotazioniNonSovrapposte(autoId, dataInizio, dataFine);
		if (count == 0)
			return true;
		return false;
	}
	
	 Optional<Auto> findByTarga(String targa);

}
