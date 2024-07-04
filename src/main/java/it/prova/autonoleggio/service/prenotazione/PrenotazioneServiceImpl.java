package it.prova.autonoleggio.service.prenotazione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.autonoleggio.exception.PrenotazioneNotfoundException;
import it.prova.autonoleggio.model.Prenotazione;
import it.prova.autonoleggio.repository.prenotazione.PrenotazioneRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	@Override
	public void annullaPrenotazione(Prenotazione input) {
		Prenotazione prenotazione = prenotazioneRepository.findById(input.getId()).orElse(null);
		if (prenotazione.equals(null))
			throw new PrenotazioneNotfoundException();
		prenotazione.setAnnullata(true);
		prenotazioneRepository.save(prenotazione);
		

	}

	@Override
	public List<Prenotazione> listAllPrenotazioni() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prenotazione modificaPrenotazione(Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prenotazione eliminaPrenotazione(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prenotazione> listaStoricoPrenotazioni() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prenotazione insertPrenotazione(Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		return null;
	}

}
