package com.example.demo.Exception;
/**
 * Eccezione personalizzata lanciata quando nella rubrica non sono presenti contatti.
 */
public class ListaVuotaException extends RuntimeException{

	public ListaVuotaException(String message) {
		super(message);
		
	}

}
