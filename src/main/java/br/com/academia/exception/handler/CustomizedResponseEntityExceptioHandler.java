package br.com.academia.exception.handler;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.academia.exception.ExceptionResponse;
import br.com.academia.exception.InvalidJwtAuthenticationException;
import br.com.academia.exception.RequiredObjectIsNullException;
import br.com.academia.exception.ResourceNotFoundException;
import br.com.academia.exception.ValidFieldResponse;

@RestControllerAdvice
public class CustomizedResponseEntityExceptioHandler {
	
	@ExceptionHandler({RequiredObjectIsNullException.class, MethodArgumentNotValidException.class})
	public final ResponseEntity<ValidFieldResponse> handleAllException(MethodArgumentNotValidException ex, WebRequest request){
		BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> 
            errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage())
        );
		
		ValidFieldResponse exceptionResponse = new ValidFieldResponse(
				LocalDateTime.now(), errorMessages , request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({InvalidJwtAuthenticationException.class, 
		TokenExpiredException.class, InternalAuthenticationServiceException.class})
	public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(Exception ex, WebRequest request){
        
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler({Exception.class,IllegalStateException.class, NullPointerException.class, InvocationTargetException.class})
	public final ResponseEntity<ExceptionResponse> handleInternalServerErroException(
			Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				LocalDateTime.now(), ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}