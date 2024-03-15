package com.gestion.empresarial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empresarial.ExeptionApi;
import com.gestion.empresarial.model.Direccion;
import com.gestion.empresarial.model.PersonaFisica;
import com.gestion.empresarial.model.VentaCompraTiendaVirtual;
import com.gestion.empresarial.model.dto.VentaCompraTiendaVirtualDto;
import com.gestion.empresarial.repository.DireccionRepository;
import com.gestion.empresarial.repository.FacturaVentaRepository;
import com.gestion.empresarial.repository.VentaCompraTiendaVirtualRepository;

@RestController
@RequestMapping("/venta-compra-tv")
public class VentaCompraTiendaVirtualController {
	
	@Autowired
	private VentaCompraTiendaVirtualRepository ventaCompraTiendaVirtualRepository;
	
	@Autowired
	private FacturaVentaRepository facturaVentaRepository;
	
	@Autowired
	private PersonaController personaController;
	
	@Autowired
	private DireccionRepository direccionRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<VentaCompraTiendaVirtualDto> salvar(@RequestBody @Valid VentaCompraTiendaVirtual ventaCompraTiendaVirtual) throws ExeptionApi{
		
		ventaCompraTiendaVirtual.getPersona().setEmpresa(ventaCompraTiendaVirtual.getEmpresa());
		PersonaFisica pessoaFisica = personaController.salvarPersonaFisica(ventaCompraTiendaVirtual.getPersona()).getBody();
		ventaCompraTiendaVirtual.setPersona(pessoaFisica);
		
		ventaCompraTiendaVirtual.getDireccionCobro().setPersona(pessoaFisica);
		ventaCompraTiendaVirtual.getDireccionCobro().setEmpresa(ventaCompraTiendaVirtual.getEmpresa());
		Direccion enderecoCobranca = direccionRepository.save(ventaCompraTiendaVirtual.getDireccionCobro());
		ventaCompraTiendaVirtual.setDireccionCobro(enderecoCobranca);
		
		ventaCompraTiendaVirtual.getDireccionEntrega().setPersona(pessoaFisica);
		ventaCompraTiendaVirtual.getDireccionEntrega().setEmpresa(ventaCompraTiendaVirtual.getEmpresa());
		Direccion enderecoEntrega = direccionRepository.save(ventaCompraTiendaVirtual.getDireccionEntrega());
		ventaCompraTiendaVirtual.setDireccionEntrega(enderecoEntrega);
		
		ventaCompraTiendaVirtual.getFacturaVenta().setEmpresa(ventaCompraTiendaVirtual.getEmpresa());
//		/*Salva primeiro a venda e todo os dados*/
		ventaCompraTiendaVirtual = ventaCompraTiendaVirtualRepository.saveAndFlush(ventaCompraTiendaVirtual);
//		
//		/*Associa a venda gravada no banco com a nota fiscal*/
		ventaCompraTiendaVirtual.getFacturaVenta().setVentaCompraTiendaVirtual(ventaCompraTiendaVirtual);
		
		/*Persiste novamente as nota fiscal novamente pra ficar amarrada na venda*/
		facturaVentaRepository.saveAndFlush(ventaCompraTiendaVirtual.getFacturaVenta());
		
		VentaCompraTiendaVirtualDto compraLojaVirtualDTO = new VentaCompraTiendaVirtualDto();
		compraLojaVirtualDTO.setId(ventaCompraTiendaVirtual.getId());
		compraLojaVirtualDTO.setValorDescuento(ventaCompraTiendaVirtual.getValorDescuento());
		compraLojaVirtualDTO.setValorTotal(ventaCompraTiendaVirtual.getValorTotal());
		
		return new ResponseEntity<VentaCompraTiendaVirtualDto>(compraLojaVirtualDTO, HttpStatus.OK);
	}
	
//	@ResponseBody
//	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
//		el resto lo omite**/
//	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
//	
//		if(!facturaCompraRepository.existsById(id)) {
//			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
//		}
//		facturaCompraRepository.deleteById(id);
//		
//		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
//	
//	}
//	
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
//	@ResponseBody
//	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
//		el resto lo omite**/
//	public ResponseEntity<FacturaCompra> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
//	
//		FacturaCompra registroBuscado = facturaCompraRepository.findById(id).orElse(null);
//		
//		if(registroBuscado == null) {
//			throw new ExeptionApi("Registro no encontrado! ");
//		}
//		
//		return new ResponseEntity<FacturaCompra>(registroBuscado, HttpStatus.OK);
//	
//	}

}
