package com.gestion.empresarial.controller;

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
import com.gestion.empresarial.model.Direccion;
import com.gestion.empresarial.repository.DireccionRepository;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
	
	@Autowired
	private DireccionRepository direccionRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<Direccion> salvar(@RequestBody @Valid Direccion direccion) throws ExeptionApi{
		
		if(direccion == null) {
			throw new ExeptionApi("No ha pasado datos en el cuerpo de la solicitud");
		}
		if((direccion.getEmpresa() == null || direccion.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Direccion! ");
		}
		if((direccion.getDepartamento() == null || direccion.getDepartamento().getId() == null)) {
			throw new ExeptionApi("Informe el departamento! ");
		}
		if((direccion.getPersona() == null || direccion.getPersona().getId() == null)) {
			throw new ExeptionApi("Informe la informacion de la persona! ");
		}
		
//		if(facturaCompra.getId() == null && facturaCompraRepository.existePorNombre(facturaCompra.getDescripcionFacturaCompra().toUpperCase(), facturaCompra.getEmpresa().getId())) {
//			throw new ExeptionApi("Ya existe un registro con la descripcion: " + facturaCompra.getDescripcionFacturaCompra());
//		}
		Direccion registroSalvado = direccionRepository.save(direccion);
		
		return new ResponseEntity<Direccion>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!direccionRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		direccionRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
//	@ResponseBody
//	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
//		el resto lo omite**/
//	public ResponseEntity<List<FacturaCompra>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
//	
//		List<FacturaCompra> registrosBuscados = facturaCompraRepository.buscarPorNombre(dato.toUpperCase());
//		
//		if(registrosBuscados.isEmpty()) {
//			throw new ExeptionApi("Registro no encontrado! ");
//		}
//		
//		return new ResponseEntity<List<FacturaCompra>>(registrosBuscados, HttpStatus.OK);
//	
//	}
//	
//	@ResponseBody
//	@GetMapping(value = "/buscarPorNroFactura/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
//		el resto lo omite**/
//	public ResponseEntity<List<FacturaCompra>> buscarPorNroFactura(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
//	
//		List<FacturaCompra> registrosBuscados = facturaCompraRepository.buscarPorNroFactura(dato.toUpperCase());
//		
//		if(registrosBuscados.isEmpty()) {
//			throw new ExeptionApi("Registro no encontrado! ");
//		}
//		
//		return new ResponseEntity<List<FacturaCompra>>(registrosBuscados, HttpStatus.OK);
//	
//	}
//	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<Direccion> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		Direccion registroBuscado = direccionRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<Direccion>(registroBuscado, HttpStatus.OK);
	
	}

}
