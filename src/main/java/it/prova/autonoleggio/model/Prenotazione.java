package it.prova.autonoleggio.model;

import java.time.LocalDate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name = "auto_id", nullable = false)
	private Auto auto;
	
	@Column(name = "localita_ritiro")
	private Localita localitaRitiro;
	
	@Column(name = "localita_consegna")
	private Localita localitaConsegna;

	@Column(name = "data_inizio")
	private LocalDate dataInizio;

	@Column(name = "data_fine")
	private LocalDate dataFine;

	@Column(name = "annullata")
	private Boolean annullata = false; // Default value set to false

}
