package com.example.demo.dtoRubrica;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO che rappresenta i dati necessari alla registrazione di un utente.
 * Contiene le informazioni di base richieste durante la fase di registrazione.
 */
public class RegisterDto {
	@NotBlank(message = "il campo email non può essere vuoto, riprova")
	private String email; 
	private String ruolo;
	@NotBlank(message = "il campo password non può essere vuoto")
	private String password;
	
	public RegisterDto() {
		super();
		
	}
	
	public RegisterDto(@NotBlank(message = "il campo email non può essere vuoto, riprova") String email, String ruolo,
			@NotBlank(message = "il campo password non può essere vuoto") String password) {
		super();
		this.email = email;
		this.ruolo = ruolo;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	} 
	
	
}
