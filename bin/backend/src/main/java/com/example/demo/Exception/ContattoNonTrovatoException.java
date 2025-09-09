package com.example.demo.Exception;
/**
 * Eccezione personalizzata lanciata quando un contatto non viene trovato.
 */
public class ContattoNonTrovatoException extends RuntimeException {

	public ContattoNonTrovatoException(String message) {
		super(message);
		
	}

}
