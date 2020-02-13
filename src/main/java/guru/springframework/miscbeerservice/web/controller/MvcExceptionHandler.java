package guru.springframework.miscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex) {
		List<String> errors = new ArrayList<>(ex.getConstraintViolations().size());
		
		for (ConstraintViolation<?> e : ex.getConstraintViolations()) {
			errors.add(e.getPropertyPath() + " : " + e.getMessage());
		}
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}

}
