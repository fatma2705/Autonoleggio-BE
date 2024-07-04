package it.prova.autonoleggio.service.utente;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.prova.autonoleggio.exception.CreditMustBeZeroException;
import it.prova.autonoleggio.exception.IdNotNullForInsertException;
import it.prova.autonoleggio.exception.PasswordMismatchException;
import it.prova.autonoleggio.exception.UserNotFoundException;
import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.model.Utente;
import it.prova.autonoleggio.repository.ruolo.RuoloRepository;
import it.prova.autonoleggio.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
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
			throw new UserNotFoundException();
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
			throw new IdNotNullForInsertException();
		}
		if (utenteInstance.getRuoli() == null) {
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			ruoli.add(ruoloRepository.findByCodice(Ruolo.ROLE_CLASSIC_USER));
			utenteInstance.setRuoli(ruoli);
		}
		if (utenteInstance.getCreditoDisponibile() != 0) {
			throw new CreditMustBeZeroException();
		} else {
			utenteInstance.setCreditoDisponibile(0);
		}

		utenteInstance.setAttivo(true);
		if (!(utenteInstance.getPassword().equals(utenteInstance.getConfermaPassword()))) {
			throw new PasswordMismatchException();
		}
		utenteInstance.setConfermaPassword(passwordEncoder.encode(utenteInstance.getConfermaPassword()));
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteRepository.save(utenteInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		utenteRepository.deleteById(idToRemove);
	}

	@Override
	public Utente findByUsernameAndPassword(String username, String password) {
		return utenteRepository.findByUsernameAndPasswordAndAttivo(username, password, true);
	}

	@Override
	public Utente eseguiAccesso(String username, String password) {
		Optional<Utente> optionalUser = utenteRepository.findByUsername(username);
		Utente user = optionalUser.get();
		if (user != null && passwordEncoder.matches(password, user.getPassword()))
			return user;
		return null;
	}

	@Override
	public void changeUserAbilitation(Long utenteInstanceId) {
		Utente utenteInstance = caricaSingoloUtente(utenteInstanceId);
		if (utenteInstance == null)
			throw new UserNotFoundException();
		utenteInstance.setAttivo(false);
	}

	@Override
	public Utente findByUsername(String username) {
		return utenteRepository.findByUsername(username).orElse(null);
	}

	@Override
	public Utente aggiornaCredito(Utente utenteInstance) {
		Utente utenteReloaded = utenteRepository.findById(utenteInstance.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new UserNotFoundException();
		utenteReloaded.setCreditoDisponibile(utenteInstance.getCreditoDisponibile());
		return utenteRepository.save(utenteReloaded);
	}

}
