package it.prova.autonoleggio.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.prova.autonoleggio.model.Ruolo;
import it.prova.autonoleggio.model.Utente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class UtenteDTO {

	private Long id;

	@NotBlank(message = "{username.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{password.notblank}")
	@Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String password;

	@NotBlank(message = "{confermaPassword.notblank}")
	private String confermaPassword;

	@NotBlank(message = "{email.notblank}")
	private String email;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotNull(message = "{attivo.notblank}")
	private Boolean attivo;

	@NotNull(message = "{dataConseguimentoPatente.notnull}")
	private LocalDate dataConseguimentoPatente;

	// @NotNull(message = "{creditoDisponibile.notnull}")
	private Float creditoDisponibile;

	private Long[] ruoliIds;

	private List<PrenotazioneDTO> prenotazioni;

	public UtenteDTO(Long id, String username, String email, String nome, String cognome, Boolean attivo,
			LocalDate dataConseguimentoPatente, Float creditoDisponibile) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.attivo = attivo;
		this.creditoDisponibile = creditoDisponibile;
	}

	public Utente buildUtenteModel(boolean includeIdRoles) {
		Utente res = new Utente(id, username, password, confermaPassword, email, nome, cognome, attivo,
				dataConseguimentoPatente, creditoDisponibile);
		if (includeIdRoles && ruoliIds != null)
			res.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> new Ruolo(id)).collect(Collectors.toSet()));
		return res;
	}

	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getEmail(),
				utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getAttivo(),
				utenteModel.getDataConseguimentoPatente(), utenteModel.getCreditoDisponibile());
		if (!utenteModel.getRuoli().isEmpty())
			result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});
		return result;
	}

	

}
