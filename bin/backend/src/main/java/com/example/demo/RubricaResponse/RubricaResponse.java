package com.example.demo.RubricaResponse;

import java.util.Map;

import org.springframework.http.HttpStatus;
/**
 * Classe di risposta standard per l'applicazione Rubrica.
 * Viene utilizzata per restituire al client una risposta uniforme,
 * contenente:
 * un messaggio descrittivo
 * lo stato della richiesta
 * un oggetto dati
 * errori
 */
public class RubricaResponse {

	private String message; 
	private HttpStatus statusCode;
	private Object data; 
	private Map<String, String> errors; 
	
	public RubricaResponse() {
		super();
		
	}
	
	public RubricaResponse(String message, HttpStatus statusCode, Object data, Map<String, String> errors) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
		this.errors = errors;
	}
	
	public RubricaResponse(String message, HttpStatus statusCode, Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	} 
	
	
	
	
}
