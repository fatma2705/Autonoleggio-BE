package it.prova.autonoleggio.dto;

import java.time.LocalDate;
import java.util.List;

import it.prova.autonoleggio.model.Auto;
import it.prova.autonoleggio.model.TipoAuto;
import it.prova.autonoleggio.model.TipoMotore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoDTO {

	private Long id;

	@NotBlank(message = "{targa.notblank}")
	private String targa;

	@NotBlank(message = "{tipologia.notblank}")
	private TipoAuto tipologia;

	@NotBlank(message = "{marca.notblank}")
	private String marca;

	@NotBlank(message = "{modello.notblank}")
	private String modello;

	@NotBlank(message = "{tipoMotore.notblank}")
	private TipoMotore tipoMotore;

	@NotNull(message = "{cilindrata.notnull}")
	private Integer cilindrata;

	@NotNull(message = "{numeroPosti.notnull}")
	private Integer numeroPosti;

	@NotNull(message = "{cambioAutomatico.notnull}")
	private Boolean cambioAutomatico;

	@NotNull(message = "{dataImmatricolazione.notnull}")
	private LocalDate dataImmatricolazione;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotNull(message = "{prezzoPerGiornata.notnull}")
	private Float prezzoPerGiornata;

	private List<PrenotazioneDTO> prenotazioni;

	public AutoDTO(Long id, String targa, TipoAuto tipologia, String marca, String modello, TipoMotore tipoMotore,
			Integer cilindrata, Integer numeroPosti, Boolean cambioAutomatico, LocalDate dataImmatricolazione,
			String descrizione, Float prezzoPerGiornata) {
		this.id = id;
		this.targa = targa;
		this.tipologia = tipologia;
		this.marca = marca;
		this.modello = modello;
		this.tipoMotore = tipoMotore;
		this.cilindrata = cilindrata;
		this.numeroPosti = numeroPosti;
		this.cambioAutomatico = cambioAutomatico;
		this.dataImmatricolazione = dataImmatricolazione;
		this.descrizione = descrizione;
		this.prezzoPerGiornata = prezzoPerGiornata;
	}

	public Auto buildAutoModel() {
		return Auto.builder().id(this.id).targa(this.targa).tipologia(this.tipologia).marca(this.marca)
				.modello(this.modello).tipoMotore(this.tipoMotore).cilindrata(this.cilindrata)
				.numeroPosti(this.numeroPosti).cambioAutomatico(this.cambioAutomatico)
				.dataImmatricolazione(this.dataImmatricolazione).descrizione(this.descrizione)
				.prezzoPerGiornata(this.prezzoPerGiornata).build();
	}

	public static AutoDTO buildAutoDTOFromModel(Auto model) {
		return new AutoDTO(model.getId(), model.getTarga(), model.getTipologia(), model.getMarca(), model.getModello(),
				model.getTipoMotore(), model.getCilindrata(), model.getNumeroPosti(), model.getCambioAutomatico(),
				model.getDataImmatricolazione(), model.getDescrizione(), model.getPrezzoPerGiornata());
	}

}
