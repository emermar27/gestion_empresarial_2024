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
import com.gestion.empresarial.model.FacturaCompra;
import com.gestion.empresarial.repository.FacturaCompraRepository;

@RestController
@RequestMapping("/factura-compra")
public class FacturaCompraController {
	
	@Autowired
	private FacturaCompraRepository facturaCompraRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<FacturaCompra> salvar(@RequestBody @Valid FacturaCompra facturaCompra) throws ExeptionApi{
		
		if(facturaCompra == null) {
			throw new ExeptionApi("La Factura de Compra no puede ser NUll");
		}
		if(facturaCompra.getNroFacturaCompra() == null) {
			throw new ExeptionApi("Por favor informe el Numero de Factura de Compra!");
		}
		if(facturaCompra.getDescripcionFacturaCompra() == null) {
			throw new ExeptionApi("Por favor informe la descripcion de la Factura de Compra!");
		}
		if(facturaCompra.getId() == null && facturaCompraRepository.existePorNombre(facturaCompra.getDescripcionFacturaCompra().toUpperCase(), facturaCompra.getEmpresa().getId())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + facturaCompra.getDescripcionFacturaCompra());
		}
		if((facturaCompra.getEmpresa() == null || facturaCompra.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Factura de Compra! ");
		}
		if((facturaCompra.getCuentaPagar() == null || facturaCompra.getCuentaPagar().getId() == null)) {
			throw new ExeptionApi("Informe la Cuenta por Pagar! ");
		}
		if((facturaCompra.getPersona() == null || facturaCompra.getPersona().getId() == null)) {
			throw new ExeptionApi("Informe la informacion de la persona! ");
		}
		
		FacturaCompra registroSalvado = facturaCompraRepository.save(facturaCompra);
		
		return new ResponseEntity<FacturaCompra>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!facturaCompraRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		facturaCompraRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<FacturaCompra>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<FacturaCompra> registrosBuscados = facturaCompraRepository.buscarPorNombre(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<FacturaCompra>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorNroFactura/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<FacturaCompra>> buscarPorNroFactura(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<FacturaCompra> registrosBuscados = facturaCompraRepository.buscarPorNroFactura(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<FacturaCompra>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<FacturaCompra> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		FacturaCompra registroBuscado = facturaCompraRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<FacturaCompra>(registroBuscado, HttpStatus.OK);
	
	}

}
