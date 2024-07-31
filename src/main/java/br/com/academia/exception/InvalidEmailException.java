package br.com.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String ex) {
		super(ex);
	}
}
