package com.gestion.empresarial;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gestion.empresarial.model.dto.ObjetoErrorDto;
import com.gestion.empresarial.service.SendEmailService;

/*CAPTURA ERRORES DE LA API*/
@RestControllerAdvice
@ControllerAdvice
public class ControlException extends ResponseEntityExceptionHandler{

	@Autowired
	private SendEmailService sendEmailService;
	
	@ExceptionHandler(ExeptionApi.class)
	public ResponseEntity<Object> handleExceptionCustom (ExeptionApi ex){
		
		ObjetoErrorDto objetoErrorDto = new ObjetoErrorDto();
		
		objetoErrorDto.setError(ex.getMessage());
		objetoErrorDto.setCodigo(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(objetoErrorDto, HttpStatus.OK);
		
	}

	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ObjetoErrorDto objetoErrorDto = new ObjetoErrorDto();
		
		String msg = "";
		
		if(ex instanceof MethodArgumentNotValidException) {
			
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for(ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
			
		}else if(ex instanceof HttpMessageNotReadableException) {
			msg = "No ha pasado datos para el cuerpo de solicitud";
		}else {
			msg = ex.getMessage();
		}
		objetoErrorDto.setError(msg);
		objetoErrorDto.setCodigo(status.value() + "==>" + status.getReasonPhrase());
		
		ex.printStackTrace();
		
		try {
			sendEmailService.enviarEmailHtml("Error en API gestion empresarial ", ExceptionUtils.getStackTrace(ex), "emermar27@hotmail.com");
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>(objetoErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*CAPTURA ERRORES DE BASE DE DATOS*/
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){
		
		ObjetoErrorDto objetoErrorDto = new ObjetoErrorDto();
		
		String msg = "";
		
		if(ex instanceof DataIntegrityViolationException) {
			msg = "Error de integridad de la BD: " + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		}else
		if(ex instanceof ConstraintViolationException) {
			msg = "Error de llave foranea: " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		}else
		if(ex instanceof SQLException) {
			msg = "Error de SQL: " + ((SQLException) ex).getCause().getCause().getMessage();
		}else {
			msg = ex.getMessage();
		}
				
		objetoErrorDto.setError(msg);
		objetoErrorDto.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		
		ex.printStackTrace();
		
		try {
			sendEmailService.enviarEmailHtml("Error en API gestion empresarial ", ExceptionUtils.getStackTrace(ex), "emermar27@hotmail.com");
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>(objetoErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
