package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Contatto;
import com.example.demo.entity.Utente;
/**
 * Repository JPA per l'entità Contatto. 
 * Espone metodi di query per gestire i contatti presenti nel database.
 */
@Repository
public interface ContattoRepository extends JpaRepository <Contatto, Long> { 

	List <Contatto> findAllByUtente(Utente utente);
	Optional <Contatto> findByIdAndUtente(Long id, Utente utente);
	Optional <Contatto> findByNomeAndUtente(String nome, Utente utente);
	Optional <Contatto> findByCognomeAndUtente(String cognome, Utente utente);
	Optional <Contatto> findByNumeroTelefonoAndUtente(String numeroTelefono, Utente utente);
	
	
	
}
