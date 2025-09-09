package com.example.demo.dtoRubrica;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO che rappresenta i dati di login di un utente.
 * Contiene le informazioni necessarie per l'autenticazione.
 */
public class LoginDto {

	@NotBlank(message = "L'email non può essere vuota")
	private String email; 
	@NotBlank(message = "la password non può essere vuota")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
	
}
