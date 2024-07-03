package it.prova.autonoleggio.service.utente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.autonoleggio.exception.IdNotNullForInsertException;
import it.prova.autonoleggio.exception.PasswordMismatchException;
import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.model.Utente;
import it.prova.autonoleggio.repository.ruolo.RuoloRepository;
import it.prova.autonoleggio.repository.utente.UtenteRepository;

public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RuoloRepository ruoloRepository;


	@Override
	public List<Utente> listAllUtenti() {
		return (List<Utente>) utenteRepository.findAll();
	}

	@Override
	public Utente caricaSingoloUtente(Long id) {
		return utenteRepository.findById(id).orElse(null);
	}

	@Override
	public Utente caricaSingoloUtenteConRuoli(Long id) {
		return utenteRepository.findByIdConRuoli(id).orElse(null);
	}

	@Override
	public Utente aggiorna(Utente utenteInstance) {
		Utente utenteReloaded = utenteRepository.findById(utenteInstance.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new RuntimeException("Utente non trovato");
		utenteReloaded.setNome(utenteInstance.getNome());
		utenteReloaded.setCognome(utenteInstance.getCognome());
		utenteReloaded.setDataConseguimentoPatente(utenteInstance.getDataConseguimentoPatente());
		utenteReloaded.setEmail(utenteInstance.getEmail());
		utenteReloaded.setRuoli(utenteInstance.getRuoli());
		return utenteRepository.save(utenteReloaded);
	}
	@Override
	public void inserisciNuovo(Utente utenteInstance) {
		if (utenteInstance.getId() != null) {
			throw new IdNotNullForInsertException("Id must be null for insert operation");
		}
		if (utenteInstance.getRuoli() == null) {
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			ruoli.add(ruoloRepository.findByCodice(Ruolo.ROLE_CLASSIC_USER));
			utenteInstance.setRuoli(ruoli);
		}
		utenteInstance.setAttivo(true);
		if (!(utenteInstance.getPassword().equals(utenteInstance.getConfermaPassword()))){
			throw new PasswordMismatchException("Passwords do not match. Please ensure both password fields are identical");
		}
		utenteInstance.setConfermaPassword(passwordEncoder.encode(utenteInstance.getConfermaPassword()));
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteRepository.save(utenteInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente eseguiAccesso(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserAbilitation(Long utenteInstanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utente findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
