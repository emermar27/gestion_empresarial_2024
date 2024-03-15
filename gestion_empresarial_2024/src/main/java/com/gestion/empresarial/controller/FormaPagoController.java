package com.gestion.empresarial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empresarial.ExeptionApi;
import com.gestion.empresarial.model.FormaPago;
import com.gestion.empresarial.repository.FormaPagoRepository;

@RestController
@RequestMapping("/forma-pago")
public class FormaPagoController {
	
	@Autowired
	private FormaPagoRepository formaPagoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<FormaPago> salvar(@RequestBody @Valid FormaPago formaPago) throws ExeptionApi{
		
		if(formaPago == null) {
			throw new ExeptionApi("No ha pasado datos en la solicitud del Body");
		}
		if(formaPago.getId() == null && formaPagoRepository.existeUnaFormaDePago(formaPago.getDescripcionFormaPago().toUpperCase(), formaPago.getEmpresa().getId())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + formaPago.getDescripcionFormaPago());
		}
		if((formaPago.getEmpresa() == null || formaPago.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece el registro! ");
		}
		
		FormaPago registroSalvado = formaPagoRepository.save(formaPago);
		
		return new ResponseEntity<FormaPago>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!formaPagoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		formaPagoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<FormaPago>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<FormaPago> registrosBuscados = formaPagoRepository.buscarNombre(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<FormaPago>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<FormaPago> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		FormaPago registroBuscado = formaPagoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<FormaPago>(registroBuscado, HttpStatus.OK);
	
	}

}
