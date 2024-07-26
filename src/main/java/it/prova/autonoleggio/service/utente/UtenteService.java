package it.prova.autonoleggio.service.utente;

import java.util.List;

import it.prova.autonoleggio.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public Utente caricaSingoloUtenteConRuoli(Long id);

	public Utente aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idToRemove);

	public Utente findByUsernameAndPassword(String username, String password);

	public Utente eseguiAccesso(String username, String password);

	public void changeUserAbilitation(Long utenteInstanceId);

	public Utente findByUsername(String username);
	
	public void aggiornaCredito(Float credit , String username);

}