package it.prova.autonoleggio.security.auth;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.autonoleggio.dto.UtenteDTO;
import it.prova.autonoleggio.security.JWTUtil;
import it.prova.autonoleggio.security.dto.UtenteAuthDTO;
import it.prova.autonoleggio.service.utente.UtenteService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody UtenteAuthDTO body) {
		try {
			 //Creating the Authentication Token which will contain the credentials for
			 //authenticating

			String token = "";
			// Se siamo qui posso tranquillamente generare il JWT Token
			if(utenteService.eseguiAccesso(body.getUsername(), body.getPassword()) != null) {
				token = jwtUtil.generateToken(body.getUsername());
			} else {
				throw new RuntimeException("Auth failed for Username: " + body.getUsername());
			}
			
			// Respond with the JWT
			return Collections.singletonMap("jwt", token);
			
		} catch (AuthenticationException authExc) {
			// Auhentication Failed
			throw new RuntimeException("Invalid Login Credentials. "+ authExc.getMessage());
		}

	}
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody UtenteDTO nuovoUtente) {
		utenteService.inserisciNuovo(nuovoUtente.buildUtenteModel(false));
	}

}