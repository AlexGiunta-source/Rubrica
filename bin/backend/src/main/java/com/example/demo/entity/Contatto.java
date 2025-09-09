package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * Entità contatto. 
 * Contenente:
 * id identificativo del contatto
 * nome 
 * cognome 
 * numero telefono
 * 
 * ogni contatto è associato a un untente tramite relazione @ManyToOne
 */
@Entity
@Table(name = "contatto")
public class Contatto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id; 
	@Column(name = "nome")
	private String nome; 
	@Column(name = "cognome")
	private String cognome; 
	@Column(name = "numeroTelefono")
	private String numeroTelefono;
	
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
	

	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Contatto() {
		super();
		
	}
	public Contatto(long id, String nome, String cognome, String numeroTelefono) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
