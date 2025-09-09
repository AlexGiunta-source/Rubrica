package com.example.demo.Exception;

/**
 * Eccezione personalizzata lanciata quando l'utente non viene trovato.
 */
public class UtenteNonTrovatoException extends RuntimeException {

	public UtenteNonTrovatoException(String message) {
		super(message);
		
	}

	
}
