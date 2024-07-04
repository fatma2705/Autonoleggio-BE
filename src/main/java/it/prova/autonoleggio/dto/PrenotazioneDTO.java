package it.prova.autonoleggio.dto;

import java.time.LocalDate;

import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.model.Prenotazione;
import it.prova.autonoleggio.model.Utente;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrenotazioneDTO {

	private Long id;

	@NotNull(message = "{utente.notnull}")
	private Utente utente;

	@NotNull(message = "{auto.notnull}")
	private Auto auto;

	@NotNull(message = "{dataInizio.notnull}")
	private LocalDate dataInizio;

	@NotNull(message = "{dataFine.notnull}")
	private LocalDate dataFine;

	@NotNull(message = "{annullata.notnull}")
	private Boolean annullata;

	public Prenotazione buildPrenotazioneModel() {
		return Prenotazione.builder().id(this.id).utente(this.utente).auto(this.auto).dataInizio(this.dataInizio)
				.dataFine(this.dataFine).annullata(this.annullata).build();

	}

	public static PrenotazioneDTO buildPrenotazioneDTOFromModel(Prenotazione model) {
		return new PrenotazioneDTO(model.getId(), model.getUtente(), model.getAuto(), model.getDataInizio(),
				model.getDataFine(), model.getAnnullata());
	}

}
