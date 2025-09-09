package com.example.demo.Exception;
/**
 * Eccezione personalizzata lanciata quando un contatto è già presente nella rubrica.
 */
public class ContattoPresenteException extends RuntimeException {

	public ContattoPresenteException(String message) {
		super(message);
		
	}

}
