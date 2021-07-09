package net.endrigo.delivery.server.controller.exception;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.endrigo.delivery.server.exception.DisabledAccountException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), error, request.getContextPath());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> security(BadCredentialsException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	
	@ExceptionHandler(DisabledAccountException.class)
	public ResponseEntity<StandardError> security(DisabledAccountException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        String campo = ((FieldError) error).getField();
        if (campo.equals("name")) campo = "nome";
        if (campo.equals("password")) campo = "senha";
        String mensagem = "O campo " + campo + " " + error.getDefaultMessage();
        
        return super.handleExceptionInternal(ex, mensagem, headers, status, request);
    }

}
