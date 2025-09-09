package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.RubricaResponse.RubricaResponse;
import com.example.demo.RubricaService.UtenteService;
import com.example.demo.dtoRubrica.LoginDto;
import com.example.demo.dtoRubrica.RegisterDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
/**
 *Controller REST responsabile della gestione delle operazioni di autenticazione
 * e registrazione degli utenti. 
 * Espone le API per:
 * Registrazione di un nuovo utente
 * Login dell'utente
 * Logout dell'utente
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService utenteService;
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	/**
	 * Registra un nuovo utente
	 * @param registerDto dati necessari per la registrazione dell'utente
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e utenteRegistrato
	 * @throws DataIntegrityViolationException lancia l'errore se un utente è già presente con la stessa email
	 * @throws Exception in caso di errore generico
	 */
	@PostMapping("/register") 
	public ResponseEntity<RubricaResponse> register(@RequestBody @Valid RegisterDto registerDto){ 
		try{
			RegisterDto utenteRegistrato = utenteService.register( registerDto.getEmail(), registerDto.getPassword());
			return ResponseEntity.ok().body(new RubricaResponse("Utente registrato correttamente", HttpStatus.OK, utenteRegistrato));
		}catch(DataIntegrityViolationException error){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new RubricaResponse("Email già registrata", HttpStatus.CONFLICT, null));
		}catch(Exception errore) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RubricaResponse("Errore durante la registrazione", HttpStatus.INTERNAL_SERVER_ERROR, null));
		}
	}
	/**
	 * Effettua il login di un utente autenticandolo
	 * @param loginDto dati necessari per effettuare il login
	 * @param request oggetto usato per gestire la sessione
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e l'email
	 */
	@PostMapping("/login") 
    public ResponseEntity<RubricaResponse> login (@RequestBody @Valid LoginDto loginDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return ResponseEntity.ok().body(new RubricaResponse("Login effettuato  correttamente", HttpStatus.OK, loginDto.getEmail()));

        } catch (AuthenticationException e) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RubricaResponse("Credenziali errate", HttpStatus.UNAUTHORIZED, null));
        }
    }
	/**
	 * Effettua il logout dell'utente, invalidando la sessione
	 * @param request oggetto usato per gestire la sessione
	 * @return ResponseEntity contenente il messaggio di conferma  e  lo status
	 */
	@PostMapping("/logout")
	public ResponseEntity <RubricaResponse> logout(HttpServletRequest request){
		request.getSession().invalidate();
		return ResponseEntity.ok().body(new RubricaResponse("Logout effettuato con successo", HttpStatus.OK, null));
	}
}
