package it.prova.autonoleggio.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.service.auto.AutoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auto")
public class AutoController {

	@Autowired
	private AutoService autoService;

	@GetMapping("/listAll")
	public List<AutoDTO> listAll() {
		return autoService.listAllAuto().stream().map(auto -> {
			return AutoDTO.buildAutoDTOFromModel(auto);
		}).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AutoDTO inserisciNuovo(@Valid @RequestBody AutoDTO input) {
		Auto auto = input.buildAutoModel();
		auto = autoService.inserisciNuovo(auto);
		return AutoDTO.buildAutoDTOFromModel(auto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id", required = true) Long id) {
		autoService.rimuovi(id);
	}

	@GetMapping("/{id}")
	public AutoDTO getById(@PathVariable(name = "id", required = true) Long id) {
		return AutoDTO.buildAutoDTOFromModel(autoService.caricaSingolaAuto(id));
	}

	@PutMapping
	public AutoDTO aggiorna(@Valid @RequestBody AutoDTO input) {
		Auto aggiornata = input.buildAutoModel();
		aggiornata = autoService.aggiorna(aggiornata);
		return AutoDTO.buildAutoDTOFromModel(aggiornata);
	}

	@PostMapping("/search")
	public List<AutoDTO> searchAuto(@RequestBody AutoDTO example) {
		Auto autoModel = example.buildAutoModel();
		return autoService.findByExample(autoModel).stream().map(auto -> {
			return AutoDTO.buildAutoDTOFromModel(auto);
		}).collect(Collectors.toList());
	}

}