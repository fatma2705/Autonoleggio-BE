package it.prova.autonoleggio.service.auto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.autonoleggio.exception.AutoNotFoundException;
import it.prova.autonoleggio.exception.IdNotNullForInsertException;
import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.repository.auto.AutoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AutoServiceImpl implements AutoService {

	@Autowired
	private AutoRepository autoRepository;

	@Override
	public List<Auto> listAllAuto() {
		return (List<Auto>) autoRepository.findAll();
	}

	@Override
	public Auto caricaSingolaAuto(Long id) {
		Auto auto = autoRepository.findById(id).orElse(null);
		if (auto.equals(null))
			throw new AutoNotFoundException();
		return auto;
	}

	@Override
	public Auto aggiorna(Auto autoInstance) {
		Auto auto = autoRepository.findById(autoInstance.getId()).orElse(null);
		if (auto.equals(null))
			throw new AutoNotFoundException();
		return autoRepository.save(autoInstance);
	}

	@Override
	public Auto inserisciNuovo(Auto autoInstance) {
		if (autoInstance.getId() != null)
			throw new IdNotNullForInsertException();
		return autoRepository.save(autoInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		Auto auto = autoRepository.findById(idToRemove).orElse(null);
		if (auto.equals(null))
			throw new AutoNotFoundException();
		autoRepository.deleteById(idToRemove);
	}

	@Override
	public List<Auto> findByExample(Auto example) {
		return autoRepository.findByExample(example.getTarga(), example.getTipologia(), example.getMarca(),
				example.getModello(), example.getCilindrata(), example.getTipoMotore(), example.getNumeroPosti(),
				example.getCambioAutomatico(), example.getDataImmatricolazione(), example.getDescrizione(),
				example.getPrezzoPerGiornata());
	}

	@Override
	public Optional<Auto> findByTarga(String targa) {
		return autoRepository.findByTarga(targa);
	}

}
