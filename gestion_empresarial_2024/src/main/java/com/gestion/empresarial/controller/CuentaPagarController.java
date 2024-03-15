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
import com.gestion.empresarial.model.CuentaPagar;
import com.gestion.empresarial.repository.CuentaPagarRepository;

@RestController
@RequestMapping("/cuenta-pagar")
public class CuentaPagarController {
	
	@Autowired
	private CuentaPagarRepository cuentaPagarRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<CuentaPagar> salvar(@RequestBody @Valid CuentaPagar cuentaPagar) throws ExeptionApi{
		
		if(cuentaPagar == null) {
			throw new ExeptionApi("La Cuenta a Pagar no puede ser NUll");
		}
		if(cuentaPagar.getDesCuentaPagar() == null) {
			throw new ExeptionApi("Por favor informe la descripcion de la cuenta a pagar");
		}
		if(cuentaPagar.getId() == null && cuentaPagarRepository.existePorNombre(cuentaPagar.getDesCuentaPagar().toUpperCase(), cuentaPagar.getEmpresa().getId())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + cuentaPagar.getDesCuentaPagar());
		}
		if((cuentaPagar.getEmpresa() == null || cuentaPagar.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Cuenta a Pagar! ");
		}
		if((cuentaPagar.getProveedor() == null || cuentaPagar.getProveedor().getId() == null)) {
			throw new ExeptionApi("Informe la informacion del proveedor! ");
		}
		if((cuentaPagar.getPersona() == null || cuentaPagar.getPersona().getId() == null)) {
			throw new ExeptionApi("Informe la informacion de la persona! ");
		}
		
		CuentaPagar registroSalvado = cuentaPagarRepository.save(cuentaPagar);
		
		return new ResponseEntity<CuentaPagar>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!cuentaPagarRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		cuentaPagarRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<CuentaPagar>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<CuentaPagar> registrosBuscados = cuentaPagarRepository.buscarPorNombre(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<CuentaPagar>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<CuentaPagar> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		CuentaPagar registroBuscado = cuentaPagarRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<CuentaPagar>(registroBuscado, HttpStatus.OK);
	
	}

}
