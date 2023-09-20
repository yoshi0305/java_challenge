package jp.co.axa.apidemo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class BadRequestExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleInvalidArgument(ConstraintViolationException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", "Invalid value");
		return errorMap;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public Map<String, String> handleInvalidArgument(EmptyResultDataAccessException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", "Not Found");
		return errorMap;
	}
}