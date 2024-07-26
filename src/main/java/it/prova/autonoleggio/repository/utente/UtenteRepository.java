package it.prova.autonoleggio.repository.utente;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.prova.autonoleggio.model.Utente;
import jakarta.transaction.Transactional;

public interface UtenteRepository extends CrudRepository<Utente, Long>, JpaRepository<Utente, Long> {

	@Query("from Utente u left join fetch u.ruoli where u.username = ?1")
	Optional<Utente> findByUsername(String username);

	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);

	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndAttivo(String username, String password, Boolean attivo);
	
	 @Transactional
	    @Modifying
	    @Query("UPDATE Utente u SET u.creditoDisponibile = :creditoAggiunto WHERE u.id = :utenteId")
	    void aggiungiCredito(@Param("utenteId") Long utenteId, @Param("creditoAggiunto") double creditoAggiunto);
	}


