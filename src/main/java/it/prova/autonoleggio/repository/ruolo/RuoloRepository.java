package it.prova.autonoleggio.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.autonoleggio.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {

	Ruolo findByCodice(String codice);

}
