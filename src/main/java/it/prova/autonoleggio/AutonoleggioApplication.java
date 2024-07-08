package it.prova.autonoleggio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.model.Utente;
import it.prova.autonoleggio.service.ruolo.RuoloService;
import it.prova.autonoleggio.service.utente.UtenteService;

@SpringBootApplication
public class AutonoleggioApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(AutonoleggioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (ruoloServiceInstance.cercaPerCodice(Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerCodice(Ruolo.ROLE_CLASSIC_USER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_CLASSIC_USER));
		}
		
		Ruolo ruolo = ruoloServiceInstance.caricaSingoloElemento(1L);
		Utente admin = utenteServiceInstance.findByUsername("adminUsername");
		if (admin != null && admin.getRuoli().contains(ruolo)) {
			System.out.println("Admin admin già impostato");
		}else {
			Set<Ruolo> ruoli =  new HashSet<>();
			ruoli.add(ruolo);
			Utente setAdmin = new Utente("adminUsername", "adminPassword", "adminPassword", "admin@example.com", "AdminNome", "AdminCognome",true,ruoli);
			utenteServiceInstance.inserisciNuovo(setAdmin);
			System.out.println("admin  settato");
		}
		
	}
}
