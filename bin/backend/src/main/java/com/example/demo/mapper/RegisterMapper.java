package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dtoRubrica.RegisterDto;
import com.example.demo.entity.Utente;


/**
 * Mapper di RegisterDto
 * converte l'entità Utente in RegisterDto e viceversa
 */
@Component
public class RegisterMapper {
	
	@Autowired
	PasswordEncoder passwordEncoder; 

	public Utente toEntity(RegisterDto dto) { 
	
		Utente entitaUtente = new Utente();
		entitaUtente.setEmail(dto.getEmail());
		entitaUtente.setPassword(passwordEncoder.encode(dto.getPassword()));
		return entitaUtente;
		
	}
	
	public RegisterDto toDto(Utente utente) {
		RegisterDto registerDto = new RegisterDto();
		registerDto.setEmail(utente.getEmail());
		registerDto.setRuolo(utente.getRuolo());
		return registerDto;
		
	}
}
