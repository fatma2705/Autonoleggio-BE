package it.prova.autonoleggio.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utente")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "conferma_password")
	private String confermaPassword;

	@Column(name = "email")
	private String email;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "attivo")
	private Boolean attivo;

	@Column(name = "data_conseguimento_patente")
	private LocalDate dataConseguimentoPatente;

	@Column(name = "credito_disponibile")
	private float creditoDisponibile;

	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "id"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	@JsonIgnore
	@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
	private List<Prenotazione> prenotazioni;

	public Utente(String username, String password, String confermaPassword, String email, String nome, String cognome,
			Boolean attivo, Set<Ruolo> ruoli) {
		this.username = username;
		this.password = password;
		this.confermaPassword = confermaPassword;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.attivo = attivo;
		this.ruoli = ruoli;
	}

	public Utente(Long id, String username, String password, String confermaPassword, String email, String nome,
			String cognome, Boolean attivo, LocalDate dataConseguimentoPatente, Set<Ruolo> ruoli) {
		this(username, password, confermaPassword, email, nome, cognome, attivo, ruoli);
		this.dataConseguimentoPatente = dataConseguimentoPatente;
		this.id = id;
	}

	public Utente(Long id , String username, String password, String confermaPassword, String email, String nome, String cognome,
			Boolean attivo, LocalDate dataConseguimentoPatente) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.confermaPassword = confermaPassword;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.attivo = attivo;
		this.dataConseguimentoPatente = dataConseguimentoPatente;
	}
	
	public Utente(Long id, String username, String password, String confermaPassword, String email, String nome,
			String cognome, Boolean attivo, LocalDate dataConseguimentoPatente, Float creditoDisponibile) {
		this(id,username,password,confermaPassword,email,nome,cognome,attivo,dataConseguimentoPatente);
		this.creditoDisponibile = creditoDisponibile;
	}

	public boolean isAdmin() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
				return true;
		}
		return false;
	}


}
