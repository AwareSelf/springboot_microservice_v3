package vw.nama.web.MVCdemo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //interceptor
public class ResponseEntityExceptionHandlerAdvice extends ResponseEntityExceptionHandler  
{

	@ExceptionHandler(EmployeeNotFoundException.class)
	ResponseEntity<Object> handleEmpNotFoundeException(EmployeeNotFoundException e)
	{
				
		  Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", e.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<Object> handleRuntimeException(RuntimeException e)
	{
				
		  Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", e.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	ResponseEntity<Object> handleRuntimeException(jakarta.validation.ConstraintViolationException ex)
	{
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("TimeStamp", LocalDateTime.now());
		body.put("Message", "bean validation exception: "+ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{

		  Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());


		return new ResponseEntity<>(body, HttpStatus.NOT_EXTENDED);

	}

}
