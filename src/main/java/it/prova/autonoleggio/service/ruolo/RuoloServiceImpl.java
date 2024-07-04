package it.prova.autonoleggio.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.autonoleggio.exception.IdNotNullForInsertException;
import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.repository.ruolo.RuoloRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;

	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		if (ruoloInstance.getId() != null) {
			throw new IdNotNullForInsertException();
		}
		ruoloRepository.save(ruoloInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		ruoloRepository.deleteById(idToRemove);
	}

	@Override
	public Ruolo cercaPerCodice(String codice) {
		return ruoloRepository.findByCodice(codice);
	}

}
