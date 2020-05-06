/*********************************************************************************************************************************************************************************************
* @author:Kunal Maheshwari
* Description: It is a controller class for handling the exceptions which will give appropriate messages when some unlikely condition occurs.
**********************************************************************************************************************************************************************************************/
package com.cg.onlinewallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = UnauthorizedAccessException.class)
	public ResponseEntity<Object> handleNullException(UnauthorizedAccessException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidException.class)
	public ResponseEntity<Object> handleInvalidException(InvalidException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = WrongValueException.class)
	public ResponseEntity<Object> handleWrongValueException(WrongValueException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}