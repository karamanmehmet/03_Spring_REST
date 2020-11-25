package com.cybertek.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	  @ExceptionHandler(value = {MyCustomException.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ApiErrorResponse resourceNotFoundException(MyCustomException ex, WebRequest request) {
		  ApiErrorResponse message = new ApiErrorResponse(
	        400,
	        LocalDateTime.now(),
	        ex.getMessage(),
	        "No such product with this id");
	    
	    return message;
	  }
}
