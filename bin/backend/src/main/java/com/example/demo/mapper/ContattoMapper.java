package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dtoRubrica.ContattoDto;
import com.example.demo.entity.Contatto;
/**
 * Mapper di ContattoDto
 * converte l'entità contatto in ContattoDto e viceversa
 */
@Component
public class ContattoMapper  { 
	
	public ContattoDto toDto(Contatto contatto) { 
		
		ContattoDto contattoDto = new ContattoDto();  
		contattoDto.setId(contatto.getId());
		contattoDto.setNome(contatto.getNome());
		contattoDto.setCognome(contatto.getCognome());
		contattoDto.setNumeroTelefono(contatto.getNumeroTelefono());
		return contattoDto; 
	}
	
	public Contatto fromDto(ContattoDto contattoDto ) { 
		Contatto contatto = new Contatto();
		contatto.setNome(contattoDto.getNome());
		contatto.setCognome(contattoDto.getCognome());
		contatto.setNumeroTelefono(contattoDto.getNumeroTelefono()); 
		return contatto; 
	}
	 
	 
}
