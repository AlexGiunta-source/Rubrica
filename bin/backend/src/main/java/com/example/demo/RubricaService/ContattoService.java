package com.example.demo.RubricaService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.ContattoNonTrovatoException;
import com.example.demo.Exception.ContattoPresenteException;
import com.example.demo.Exception.ListaVuotaException;
import com.example.demo.Exception.UtenteNonTrovatoException;
import com.example.demo.dtoRubrica.ContattoDto;
import com.example.demo.entity.Contatto;
import com.example.demo.entity.Utente;
import com.example.demo.mapper.ContattoMapper;
import com.example.demo.repository.ContattoRepository;
import com.example.demo.repository.UtenteRepository;

import jakarta.transaction.Transactional;

@Service
public class ContattoService {

	private ContattoRepository contattoRepository;
	private UtenteRepository utenteRepository; 
	private ContattoMapper contattoMapper;

	public ContattoService(ContattoRepository contattoRepository, UtenteRepository utenteRepository, ContattoMapper contattoMapper) {
		super();
		this.contattoRepository = contattoRepository;
		this.utenteRepository = utenteRepository;
		this.contattoMapper = contattoMapper;
	}
	
	
	/**
	 * Metodo che cerca il numeroTelefono dell'entità Contatto in base all entità Utente collegata
	 * cerca l'entità Utente, se non lo trova lancia errore
	 * cerca il numero di telefono dell'entità Contatto, se non lo trova lancia errore
	 * @param numeroTelefono corrisponde al numero di telefono da cercare
	 * @param email dell'entità Utente
	 * @return contatto convertito in dto
	 * @throws 
	 * @throws 
	 */
	@Transactional
	public ContattoDto cercaContattoPerNumeroTelefono(String email,String numeroTelefono){
		Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato"));
		Contatto contatto = contattoRepository.findByNumeroTelefonoAndUtente(numeroTelefono, convalidaUtente).orElseThrow(()-> new ContattoNonTrovatoException("Impossibile completare l'operazione, nessun contatto trovato"));
		return contattoMapper.toDto(contatto);
	}
	

	/**
	 * Metodo che cerca il nome dell'entità Contatto in base all entità Utente collegata. 
	 * cerca l'entità Utente, se non lo trova lancia errore
	 * cerca il nome dell'entità Contatto, se non lo trova lancia errore
	 * @param email dell'entità Utente
	 * @param nome dell'entità Contatto
	 * @return contatto convertito in dto
	 * @throws
	 * @throws 
	 */
	@Transactional
	public ContattoDto cercaContattoPerNome(String email, String nome){
		Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato"));
		Contatto contatto = contattoRepository.findByNomeAndUtente(nome, convalidaUtente).orElseThrow(()-> new ContattoNonTrovatoException("Impossibile completare l'operazione, nessun contatto trovato"));
		return contattoMapper.toDto(contatto);
	}
	
	
	/**
	 * Metodo che cerca il cognome dell'entità Contatto in base all entità Utente collegata. 
	 * cerca l'entità Utente, se non lo trova lancia errore
	 * cerca il cognome dell'entità Contatto, se non lo trova lancia errore
	 * @param email dell'entità Utente
	 * @param cognome dell'entità Contatto
	 * @return contatto convertito in dto
	 * @throws
	 * @throws 
	 */
		@Transactional
		public ContattoDto cercaContattoPerCognome(String email, String cognome){
			Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato"));
			Contatto contatto = contattoRepository.findByCognomeAndUtente(cognome, convalidaUtente).orElseThrow(()-> new ContattoNonTrovatoException("Impossibile completare l'operazione, nessun contatto trovato"));
			return contattoMapper.toDto(contatto);
		}
		
		
		/** 
		 * Metodo per aggiungere un contatto alla rubrica. 
		 * cerca l'entità Utente, se non lo trova lancia errore
		 * converte il dto ContattoDto in un'entità Contatto
		 * controlla se esiste un contatto con lo stesso numero, se esiste lancia errore
		 * @param email dell'entità Utente
		 * @param contattoDto dati in entrata necessari per il salvataggio del contatto
		 * @return contattoRepository.save(nuovoContatto), salvataggio del nuovo contatto
		 * @throws
		 * @throws
		 */
		@Transactional
		public  Contatto  salvaContatto(String email, ContattoDto contattoDto){
			Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato")); 
			Contatto nuovoContatto = contattoMapper.fromDto(contattoDto);
			boolean esisteContatto = contattoRepository.findByNumeroTelefonoAndUtente(nuovoContatto.getNumeroTelefono(), convalidaUtente).isPresent();
			if(esisteContatto) {
				throw new ContattoPresenteException("E' già presente un contatto con questo numero di telefono, riprova");
			}
			nuovoContatto.setUtente(convalidaUtente);
			return contattoRepository.save(nuovoContatto);
		}
		
		/**
		 * Metodo per restituire la lista completa dei contatti in base all'utente
		 * cerca l'entità Utente, se non lo trova lancia errore
		 * cerca tutti i contatti in base all'utente, se non è presente nessun contatto lancia errore
		 * crea una lista, per ogni contatto presente, lo converte in dto e lo aggiunge alla lista
		 * @param email dell'entità Utente
		 * @return listaContattiDto la listaDto di tutti i contatti presenti in rubrica
		 * @throws 
		 * @throws
		 */
		@Transactional
		public List <ContattoDto> mostraContattiUtente(String email) {
			Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato")); 
			List <Contatto> listaContattiCompleta = contattoRepository.findAllByUtente(convalidaUtente);
			if(listaContattiCompleta.isEmpty()) {
				throw new ListaVuotaException("Nessun contatto presente nella rubrica, riprova");
			}
			List <ContattoDto> listaContattiDto = new ArrayList<>();
			for(Contatto contatto: listaContattiCompleta){
				listaContattiDto.add(contattoMapper.toDto(contatto));
			}
			return listaContattiDto;
		}
		/**
		 * Metodo che elimina un'entità Contatto in base al suo id
		 * cerca l'entità Utente, se non lo trova lancia errore
		 * cerca l'entitò contatto per id, se non lo trova lancia errore
		 * una volta trovato il contatto, lo elimina
		 * @param contattoDto il dto in entrata che conterrà l'id da cercare
		 * @param email email dell'entità Utente
		 * @throws 
		 * @throws
		 */
		@Transactional
		public void eliminaContatto(Long id, String email) {
			Utente convalidaUtente = utenteRepository.findByEmail(email).orElseThrow(() -> new UtenteNonTrovatoException("Impossibile completare l'operazione, utente non trovato")); 
			Contatto contattoedaEliminare = contattoRepository.findByIdAndUtente(id, convalidaUtente).orElseThrow(() -> new ContattoNonTrovatoException("Impossibile completare l'operazione, nessun contatto trovato"));
			contattoRepository.delete(contattoedaEliminare);
		}
}

