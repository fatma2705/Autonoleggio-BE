package it.prova.autonoleggio.dto;

import java.time.LocalDate;

import it.prova.autonoleggio.model.Prenotazione;
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
	private Long utenteId;

	@NotNull(message = "{auto.notnull}")
	private Long autoId;

	@NotNull(message = "{dataInizio.notnull}")
	private LocalDate dataInizio;

	@NotNull(message = "{dataFine.notnull}")
	private LocalDate dataFine;

	@NotNull(message = "{annullata.notnull}")
	private Boolean annullata;

	public Prenotazione buildPrenotazioneModel() {
		return Prenotazione.builder().id(this.id).utenteId(this.utenteId).autoId(this.autoId)
				.dataInizio(this.dataInizio).dataFine(this.dataFine).annullata(this.annullata).build();

	}

	public static PrenotazioneDTO buildPrenotazioneDTOFromModel(Prenotazione model) {
		return new PrenotazioneDTO(model.getId(), model.getUtenteId(), model.getAutoId(), model.getDataInizio(),
				model.getDataFine(), model.getAnnullata());
	}

}
