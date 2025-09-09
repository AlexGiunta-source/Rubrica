package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Utente;
/**
 * Repository JPA per l'entità Utente.
 * Espone i metodi CRUD ereditati da JpaRepository
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	Optional<Utente> findByEmail(String email); 
}

