package br.com.academia.exception.handler;

import br.com.academia.exception.*;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class CustomizedResponseEntityExceptioHandler {
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ValidFieldResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, 
            WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> 
            errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage())
        );
        
        return buildResponseEntity(errorMessages, request);
    }

    @ExceptionHandler({RequiredObjectIsNullException.class, InvalidCpfLengthException.class,
			InvalidPasswordException.class, RequiredNameInvalidException.class,
			InvalidCpfException.class, InvalidEmailException.class})
    public final ResponseEntity<ValidFieldResponse> handleOtherExceptions(
            Exception ex, 
            WebRequest request) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());
        return buildResponseEntity(errorMessages, request);
    }

    private ResponseEntity<ValidFieldResponse> buildResponseEntity(List<String> errorMessages, WebRequest request) {
        ValidFieldResponse exceptionResponse = new ValidFieldResponse(
                LocalDateTime.now(), errorMessages, request.getDescription(false));
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