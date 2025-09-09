package com.example.demo.RubricaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtoRubrica.RegisterDto;
import com.example.demo.entity.Utente;
import com.example.demo.mapper.RegisterMapper;
import com.example.demo.repository.UtenteRepository;

/**
 * Service per la gestione degli utenti.
 * Fornisce metodi per salvare utenti, registrare nuovi utenti e gestire la codifica della password.
 */
@Service
public class UtenteService {

	private final RegisterMapper registerMapper; 
	
	public UtenteService(RegisterMapper registerMapper) {
		super();
		this.registerMapper = registerMapper;
	}

	@Autowired
	UtenteRepository utenteRepository; 
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public Utente  saveUtente(Utente utenteSalvato) {
		
		Utente utente= utenteRepository.save(utenteSalvato);
		return utente;
	}
	public RegisterDto register(String email, String password) {
		
		if(utenteRepository.findByEmail(email).isPresent()) { 
			throw new RuntimeException("Email già registrata"); 
		}
		Utente utente = new Utente();
		utente.setEmail(email); 
		utente.setPassword(passwordEncoder.encode(password)); 
		utente.setRuolo("ROLE_USER");
		utenteRepository.save(utente);
		return registerMapper.toDto(utente);
	}
}

	