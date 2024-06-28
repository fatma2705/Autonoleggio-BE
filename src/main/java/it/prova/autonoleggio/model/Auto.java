package it.prova.autonoleggio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auto")
public class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "targa")
	private String targa;

	@Column(name = "tipologia")
	private TipoAuto tipologia;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modello")
	private String modello;

	@Column(name = "cilindrata")
	private Integer cilindrata;

	@Column(name = "tipo_motore")
	private TipoMotore tipoMotore;

	@Column(name = "numero_posti")
	private Integer numeroPosti;

	@Column(name = "cambio_automatico")
	private Boolean cambioAutomatico;

	@Column(name = "data_immatricolazione")
	private LocalDate dataImmatricolazione;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "prezzo_per_giornata")
	private Float prezzoPerGiornata;

}