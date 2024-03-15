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
import com.gestion.empresarial.model.FacturaItemProducto;
import com.gestion.empresarial.repository.FacturaItemProductoRepository;

@RestController
@RequestMapping("/factura-item-producto")
public class FacturaItemProductoController {
	
	@Autowired
	private FacturaItemProductoRepository facturaItemProductoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<FacturaItemProducto> salvar(@RequestBody @Valid FacturaItemProducto facturaItemProducto) throws ExeptionApi{
		
		if(facturaItemProducto == null) {
			throw new ExeptionApi("La Factura Item Producto no puede ser NUll");
		}
		
		if(facturaItemProducto.getId() == null) {
			
			if((facturaItemProducto.getEmpresa() == null || facturaItemProducto.getEmpresa().getId() <= 0)) {
				throw new ExeptionApi("Informe la empresa a la que petenece los Items del Producto! ");
			}
			if((facturaItemProducto.getProducto() == null || facturaItemProducto.getProducto().getId() <= 0)) {
				throw new ExeptionApi("Informe la Cuenta por Pagar! ");
			}
			if((facturaItemProducto.getFacturaCompra() == null || facturaItemProducto.getFacturaCompra().getId() <= 0)) {
				throw new ExeptionApi("Informe los datos de la Factura! ");
			}
			if(facturaItemProductoRepository.existeUnRegistro(facturaItemProducto.getFacturaCompra().getId(), facturaItemProducto.getProducto().getId(), facturaItemProducto.getEmpresa().getId())) {
				throw new ExeptionApi("Ya existe un registro con los datos informados! ");
			}
		
		}
			
		FacturaItemProducto registroSalvado = facturaItemProductoRepository.save(facturaItemProducto);
		
		return new ResponseEntity<FacturaItemProducto>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!facturaItemProductoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		facturaItemProductoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorNroFactura/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<FacturaItemProducto>> buscarPorNroFactura(@PathVariable("dato") Long dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<FacturaItemProducto> registrosBuscados = facturaItemProductoRepository.buscarPorNroFactura(dato);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<FacturaItemProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorProducto/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<FacturaItemProducto>> buscarPorProducto(@PathVariable("dato") Long dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<FacturaItemProducto> registrosBuscados = facturaItemProductoRepository.buscarPorProducto(dato);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<FacturaItemProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<FacturaItemProducto> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		FacturaItemProducto registroBuscado = facturaItemProductoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<FacturaItemProducto>(registroBuscado, HttpStatus.OK);
	
	}

}
