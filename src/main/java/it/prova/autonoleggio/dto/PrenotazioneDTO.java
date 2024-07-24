package it.prova.autonoleggio.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.model.Localita;
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
@JsonInclude(value = Include.NON_NULL)
public class PrenotazioneDTO {

	private Long id;

	@NotNull(message = "{utente.notnull}")
	private Utente utente;

	@NotNull(message = "{auto.notnull}")
	private Auto auto;

	@NotNull(message = "{localitaRitiro.notnull}")
	private Localita localitaRitiro;

	@NotNull(message = "{localitaConsegna.notnull}")
	private Localita localitaConsegna;

	@NotNull(message = "{dataInizio.notnull}")
	private LocalDate dataInizio;

	@NotNull(message = "{dataFine.notnull}")
	private LocalDate dataFine;

	@NotNull(message = "{annullata.notnull}")
	private Boolean annullata;

	public PrenotazioneDTO(Long id, LocalDate dataInizio, LocalDate dataFine) {
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public PrenotazioneDTO(Long id, Localita localitaRitiro, Localita localitaConsegna, LocalDate dataInizio,
			LocalDate dataFine) {
		this(id, dataInizio, dataFine);
		this.localitaRitiro = localitaRitiro;
		this.localitaConsegna = localitaConsegna;
	}

	public Prenotazione buildPrenotazioneModel() {
		return Prenotazione.builder().id(this.id).utente(this.utente).auto(this.auto)
				.localitaRitiro(this.localitaRitiro).localitaConsegna(this.localitaConsegna).dataInizio(this.dataInizio)
				.dataFine(this.dataFine).annullata(this.annullata).build();

	}

	public static PrenotazioneDTO buildPrenotazioneDTOFromModel(Prenotazione model) {
		return new PrenotazioneDTO(model.getId(), model.getUtente(), model.getAuto(), model.getLocalitaRitiro(),
				model.getLocalitaConsegna(), model.getDataInizio(), model.getDataFine(), model.getAnnullata());
	}

	public static PrenotazioneDTO buildResponsePrenotazioneDTOFromModel(Prenotazione model) {
		return new PrenotazioneDTO(model.getId(), model.getLocalitaRitiro(), model.getLocalitaRitiro(),
				model.getUtente(), model.getAuto(), model.getDataInizio(), model.getDataFine());
	}

	public PrenotazioneDTO(Long id, Localita localitaRitiro, Localita localitaConsegna, Utente utente, Auto auto,
			LocalDate dataInizio, LocalDate dataFine) {
		this(id, localitaRitiro, localitaConsegna, dataInizio, dataFine);
		this.auto = auto;
		this.utente = utente;
	}

	

}
