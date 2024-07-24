package it.prova.autonoleggio.web.api;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.autonoleggio.dto.AutoDTO;
import it.prova.autonoleggio.dto.PrenotazioneDTO;
import it.prova.autonoleggio.model.Prenotazione;
import it.prova.autonoleggio.service.prenotazione.PrenotazioneService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@GetMapping("/listAdmin")
	public List<PrenotazioneDTO> listAllAdmin(Principal principal) {
		return prenotazioneService.listaStoricoPrenotazioniAdmin(principal.getName()).stream().map(prenotazione -> {
			return PrenotazioneDTO.buildPrenotazioneDTOFromModel(prenotazione);
		}).collect(Collectors.toList());

	}

	@GetMapping("/listUtente")
	public List<PrenotazioneDTO> listAllUtente(Principal principal) {
		return prenotazioneService.listaStoricoPrenotazioniUtente(principal.getName()).stream().map(prenotazione -> {
			return PrenotazioneDTO.buildPrenotazioneDTOFromModel(prenotazione);
		}).collect(Collectors.toList());

	}

	@PostMapping("/inserisci")
	@ResponseStatus(HttpStatus.CREATED)
	public PrenotazioneDTO inserisciPrenotazione(@Valid @RequestBody PrenotazioneDTO input, Principal principal) {
		Prenotazione prenotazione = input.buildPrenotazioneModel();
		prenotazione = prenotazioneService.insertPrenotazione(prenotazione, principal.getName());
		return PrenotazioneDTO.buildResponsePrenotazioneDTOFromModel(prenotazione);
	}

	@PutMapping("/annullaPrenotazione/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void annullaPrenotazione(@PathVariable(name = "id", required = true) Long id, Principal principal) {
		prenotazioneService.annullaPrenotazione(id, principal.getName());

	}

	@PutMapping
	public PrenotazioneDTO modificaPrenotazione(@Valid @RequestBody PrenotazioneDTO input, Principal principal) {
		Prenotazione aggiornata = input.buildPrenotazioneModel();
		aggiornata = prenotazioneService.modificaPrenotazione(aggiornata, principal.getName());
		return PrenotazioneDTO.buildPrenotazioneDTOFromModel(aggiornata);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id", required = true) Long id, Principal principal) {
		prenotazioneService.eliminaPrenotazione(id, principal.getName());
	}

	@GetMapping("/{id}")
	public PrenotazioneDTO getById(@PathVariable(name = "id", required = true) Long id) {
		return PrenotazioneDTO.buildPrenotazioneDTOFromModel(prenotazioneService.caricaSingolaPrenotazione(id));
	}

}
