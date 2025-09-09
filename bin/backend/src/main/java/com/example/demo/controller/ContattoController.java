package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ContattoNonTrovatoException;
import com.example.demo.Exception.ContattoPresenteException;
import com.example.demo.Exception.ListaVuotaException;
import com.example.demo.Exception.UtenteNonTrovatoException;
import com.example.demo.RubricaResponse.RubricaResponse;
import com.example.demo.RubricaService.ContattoService;
import com.example.demo.RubricaService.UtenteService;
import com.example.demo.dtoRubrica.ContattoDto;
import com.example.demo.entity.Contatto;

import jakarta.validation.Valid;
/**
 * Controller rest che gestisce le operazioni CRUD sui contatti della rubrica
 * Esponde endpoint per l'utente che vuole: 
 * eliminare un contatto nella rubrica
 * mostrare la lista intera di contatti presenti nella rubrica in base al contatto loggato
 * aggiungere un nuovo contatto nella rubrica
 * cercare un contatto per nome
 * cercare un contatto per cognome
 * cercare un contatto per numero di telefono 
 * 
 */
@RestController
public class ContattoController {
	
	
	@Autowired 
	ContattoService contattoService;
	@Autowired 
	UtenteService utenteService; 
	
	
	/**
	 * elimina un'entità di tipo Contatto tramite il suo id. 
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @param id dell'entità Contatto
	 * @throws UtenteNonTrovatoException lancia l'errore quando l'utente loggato non viene trovato
	 * @throws ContattoNonTrovatoException lancia l'errore quando il contatto non è presente nella rubrica 
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server 
	 * @return ResponseEntity contenente il messaggio di conferma e lo status
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@DeleteMapping("/eliminaContatto/{id}")
	public ResponseEntity <RubricaResponse> eliminaContatto( @AuthenticationPrincipal UserDetails userDetails ,@PathVariable Long id){
		try {
			contattoService.eliminaContatto(id, userDetails.getUsername());
				return ResponseEntity.ok().body(new RubricaResponse("Contatto eliminato con successo", HttpStatus.OK, null));
		}catch(UtenteNonTrovatoException erroreUtente){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreUtente.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(ContattoNonTrovatoException erroreContatto) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreContatto.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(Exception erroreGenerico) {
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST,null));
		}
		}
		
	
	
	/**
	 * Mostra l'intera lista dei contatti presenti nella rubrica. 
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @throws ListaVuotaException lancia l'errore quando non sono presenti contatti all'interno della rubrica
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server  
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e la lista di contatti
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/mostraListaContatti/")
	public ResponseEntity <RubricaResponse> mostraTuttiContatti(@AuthenticationPrincipal  UserDetails userDetails){ 
		try {
			 List <ContattoDto> mostraContatti  = contattoService.mostraContattiUtente(userDetails.getUsername());
			return ResponseEntity.ok().body(new RubricaResponse("Lista contatti caricata correttamente!", HttpStatus.OK, mostraContatti));
		}catch(ListaVuotaException erroreLista){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreLista.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(Exception erroreGenerico){
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST, null));
		}
		
	}
	
	/**
	 * Aggiunge un'entità di tipo Contatto alla rubrica. 
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @param contattoDto i dati in entrata necessari per creare un nuovo contatto
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e salvaContatto
	 * @throws ContattoPresenteException lancia l'errore quando è già presente lo stesso contatto
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server 
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping("/aggiungiContattoUtente/")
	public ResponseEntity <RubricaResponse> aggiungiContattoUtente(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid ContattoDto contattoDto){
		try {
			 Contatto salvaContatto  = contattoService.salvaContatto(userDetails.getUsername(), contattoDto );
			return ResponseEntity.ok().body(new RubricaResponse("Salvataggio contatto effettuato con successo!", HttpStatus.OK, salvaContatto));
		}catch(ContattoPresenteException erroreContattoPresente){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new RubricaResponse(erroreContattoPresente.getMessage(), HttpStatus.CONFLICT, null));
		}catch(Exception erroreGenerico){
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST, null));
		}
		
	}
	/**
	 * cerca un'entità di tipo Contatto per nome
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @param nome necessario per cercare l'entità per nome
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e contattoNome.
	 * @throws ContattoNonTrovatoException lancia l'errore quando il contatto non è presente nella rubrica
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server 
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/cercaContattoPerNome/{nome}")
	public ResponseEntity <RubricaResponse> cercaContattoPerNome(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String nome){ 
		try {
			 ContattoDto contattoNome  = contattoService.cercaContattoPerNome(userDetails.getUsername(), nome);
			return ResponseEntity.ok().body(new RubricaResponse("Il contatto è presente in rubrica", HttpStatus.OK, contattoNome));
		}catch(ContattoNonTrovatoException erroreContatto) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreContatto.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(Exception erroreGenerico){
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST, null));
		}
	}
	
	/**
	 * cerca un'entità di tipo Contatto per cognome
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @param cognome necessario per cercare l'entità per cognome
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e contattoCognome.
	 * @throws ContattoNonTrovatoException lancia l'errore quando il contatto non è presente nella rubrica
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server 
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/cercaContattoPerCognome/{cognome}")
	public ResponseEntity <RubricaResponse> cercaContattoPerCognome(@AuthenticationPrincipal UserDetails userDetails , @PathVariable String cognome){
		try {
			 ContattoDto contattoCognome  = contattoService.cercaContattoPerCognome(userDetails.getUsername(), cognome);
			return ResponseEntity.ok().body(new RubricaResponse("Il contatto è presente in rubrica", HttpStatus.OK, contattoCognome));
		}catch(ContattoNonTrovatoException erroreContatto) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreContatto.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(Exception erroreGenerico){
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST, null));
		}
	}
	/**
	 * cerca un'entità di tipo Contatto per numero di telefono
	 * @param userDetails contiene i dati di sicurezza riguardanti l'utente
	 * @param numeroTelefono necessario per cercare l'entità per numeroTelefono
	 * @return ResponseEntity contenente il messaggio di conferma, lo status e contattoNumeroTelefono.
	 * @throws ContattoNonTrovatoException lancia l'errore quando il contatto non è presente nella rubrica
	 * @throws Exception lancia l'errore quando si verifica un errore imprevisto del server 
	 */
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/cercaContattoPerNumeroTelefono/{numeroTelefono}")
	public ResponseEntity <RubricaResponse> cercaContattoPerNumeroTelefono(@AuthenticationPrincipal UserDetails userDetails,@PathVariable String numeroTelefono){
		try {
			 ContattoDto contattoNumeroTelefono = contattoService.cercaContattoPerNumeroTelefono(userDetails.getUsername(), numeroTelefono);
			return ResponseEntity.ok().body(new RubricaResponse("Il contatto è presente in rubrica", HttpStatus.OK, contattoNumeroTelefono));
		}catch(ContattoNonTrovatoException erroreContatto) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RubricaResponse(erroreContatto.getMessage(), HttpStatus.NOT_FOUND, null));
		}catch(Exception erroreGenerico){
			return ResponseEntity.badRequest().body(new RubricaResponse("Errore imprevisto", HttpStatus.BAD_REQUEST, null));
		}
	}
	
	
	
	
	
}
