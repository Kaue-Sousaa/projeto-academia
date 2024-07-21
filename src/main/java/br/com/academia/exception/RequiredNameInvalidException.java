package br.com.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequiredNameInvalidException extends RuntimeException{

    public RequiredNameInvalidException(String message){
        super(message);
    }
}
