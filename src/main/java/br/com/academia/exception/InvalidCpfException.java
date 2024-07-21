package br.com.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCpfException extends RuntimeException{

	public InvalidCpfException(String ex) {
		super(ex);
	}
}
