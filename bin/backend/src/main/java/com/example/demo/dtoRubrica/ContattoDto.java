package com.example.demo.dtoRubrica;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO  che rappresenta un contatto all'interno della rubrica.
 * Contiene informazioni di base come l'ID, il nome, il cognome e il numero di telefono.
 */
@Component
public class ContattoDto {
	
	private Long id; 
	@NotBlank(message = "il nome non può essere vuoto") 
	private String nome; 
	@NotBlank(message = "il cognome non può essere vuoto")
	private String cognome; 
	@NotBlank(message = "il numero di telefono non può essere vuoto")
	private String numeroTelefono;
	
	public ContattoDto() {
		super();
	
	}

	public ContattoDto(Long id, String nome, String cognome, String numeroTelefono) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
		this.id = id;
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	

}
