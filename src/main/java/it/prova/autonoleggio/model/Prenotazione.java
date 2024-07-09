package it.prova.autonoleggio.model;

import java.time.LocalDate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "prenotazione")
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name = "auto_id", nullable = false)
	private Auto auto;

	@Column(name = "data_inizio")
	private LocalDate dataInizio;

	@Column(name = "data_fine")
	private LocalDate dataFine;

	@Column(name = "annullata")
	private Boolean annullata = false; // Default value set to false

}
