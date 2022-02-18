package br.com.diogomacedo.moviesbattle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.diogomacedo.moviesbattle.exceptions.RegraDeNegocioException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> handleBussinessException(RegraDeNegocioException bussinesException) {
		return new ResponseEntity<>(bussinesException.getErro(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
