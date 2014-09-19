package be.klusjes.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerExceptionHandler {

	 @ExceptionHandler(DataIntegrityViolationException.class)
	    public String exception(Exception e) {
	 
	        return "error";
	    }
}
