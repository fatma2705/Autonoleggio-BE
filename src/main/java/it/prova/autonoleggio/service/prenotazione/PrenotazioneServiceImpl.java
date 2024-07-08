package it.prova.autonoleggio.service.prenotazione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.autonoleggio.exception.AccessDeniedException;
import it.prova.autonoleggio.exception.CarNotAvailableException;
import it.prova.autonoleggio.exception.NoBookingsFoundException;
import it.prova.autonoleggio.exception.PrenotazioneNotfoundException;
import it.prova.autonoleggio.model.Prenotazione;
import it.prova.autonoleggio.model.Utente;
import it.prova.autonoleggio.repository.auto.AutoRepository;
import it.prova.autonoleggio.repository.prenotazione.PrenotazioneRepository;
import it.prova.autonoleggio.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private AutoRepository autoRepository;

	@Override
	public void annullaPrenotazione(Long id, String username) {
		Prenotazione prenotazione = prenotazioneRepository.findById(id).orElse(null);
		if (prenotazione.equals(null))
			throw new PrenotazioneNotfoundException();
		if (!(prenotazione.getUtente().getUsername().equals(username)))
			throw new AccessDeniedException();
		prenotazione.setAnnullata(true);
		prenotazioneRepository.save(prenotazione);

	}

	@Override
	public Prenotazione modificaPrenotazione(Prenotazione input, String username) {
		Prenotazione prenotazione = prenotazioneRepository.findById(input.getId()).orElse(null);
		if (prenotazione.equals(null))
			throw new PrenotazioneNotfoundException();
		if (!(prenotazione.getUtente().getUsername().equals(username)))
			throw new AccessDeniedException();
		if (!(autoRepository.isAutoAvailable(input.getAuto().getId(), input.getDataInizio(),
				input.getDataFine())))
			throw new CarNotAvailableException();
		return prenotazioneRepository.save(input);
	}

	@Override
	public void eliminaPrenotazione(Long id, String username) {
		Prenotazione prenotazione = prenotazioneRepository.findById(id).orElse(null);
		if (prenotazione.equals(null))
			throw new PrenotazioneNotfoundException();
		if (!(prenotazione.getUtente().getUsername().equals(username)))
			throw new AccessDeniedException();
		prenotazioneRepository.deleteById(id);
	}

	@Override
	public List<Prenotazione> listaStoricoPrenotazioniAdmin(String username) {
		Utente utente = utenteRepository.findByUsername(username).orElse(null);
		if (!(utente.isAdmin()))
			throw new AccessDeniedException();
		return (List<Prenotazione>) prenotazioneRepository.findAll();
	}

	@Override
	public List<Prenotazione> listaStoricoPrenotazioniUtente(String username) {
		Utente utente = utenteRepository.findByUsername(username).orElse(null);
		List<Prenotazione> prenotazioni = prenotazioneRepository.findPrenotazioniByUtenteId(utente.getId());
		if (prenotazioni.isEmpty())
			throw new NoBookingsFoundException();
		return prenotazioni;
	}

	@Override
	public Prenotazione insertPrenotazione(Prenotazione prenotazione, String username) {
		if (!(autoRepository.isAutoAvailable(prenotazione.getAuto().getId(), prenotazione.getDataInizio(),
				prenotazione.getDataFine())))
			throw new CarNotAvailableException();
		Utente utente = utenteRepository.findByUsername(username).orElse(null);
		prenotazione.setUtente(utente);
		return prenotazioneRepository.save(prenotazione);
	}

}
