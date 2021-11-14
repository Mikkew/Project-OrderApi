package mx.com.mms.orderapi.exceptions;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.com.mms.orderapi.utils.WrapperErrorResponse;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(err -> err.getField().concat(" : ").concat(err.getDefaultMessage())).toList();
		WrapperErrorResponse<List<String>> response = new WrapperErrorResponse<>(
				status.value(), 
				LocalDateTime.now(), 
				request.getContextPath(), errors);
		return response.createResponse(status);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		WrapperErrorResponse<String> response = new WrapperErrorResponse<>(
				HttpStatus.NOT_FOUND.value(), 
				LocalDateTime.now(), 
				request.getRequestURI(),
				ex.getMessage());
		return response.createResponse(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalExceptionHandler(Exception ex, HttpServletRequest request) {
		WrapperErrorResponse<String> response = new WrapperErrorResponse<>(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				LocalDateTime.now(),
				request.getRequestURI(), 
				ex.getMessage());
		return response.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
