package com.example.demo.RubricaService;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Utente;
import com.example.demo.repository.UtenteRepository;
/**
 * Implementazione personalizzata di UserDetailsService.
 * Questa classe si occupa di caricare i dati dell'utente dal database
 * Viene utilizzata da Spring Security durante il processo di autenticazione.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UtenteRepository utenteRepository; 

	public UserDetailsServiceImpl(UtenteRepository utenteRepository) {
		super();
		this.utenteRepository = utenteRepository; 
	}
	
	@Override
	public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { 
		
		Optional <Utente> utente = utenteRepository.findByEmail(email);
		if(utente.isEmpty()) {
			throw new UsernameNotFoundException("Utente non trovato");
		}
		Utente utenti = utente.get();
		return User.builder()
				.username(utenti.getEmail())
				.password(utenti.getPassword())
				.authorities(List.of(new SimpleGrantedAuthority(utenti.getRuolo()))) 
				.build();
			
				

}
	
	
}
