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
import com.gestion.empresarial.model.EvaluacionProducto;
import com.gestion.empresarial.repository.EvaluacionProductoRepository;

@RestController
@RequestMapping("/evaluacion-producto")
public class EvaluacionProductoController {
	
	@Autowired
	private EvaluacionProductoRepository evaluacionProductoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<EvaluacionProducto> salvar(@RequestBody @Valid EvaluacionProducto evaluacionProducto) throws ExeptionApi{
		
		if(evaluacionProducto == null) {
			throw new ExeptionApi("La Evaluacion del Producto no puede ser NUll");
		}
		if((evaluacionProducto.getEmpresa() == null || evaluacionProducto.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Evaluacion del producto! ");
		}
		if((evaluacionProducto.getProducto() == null || evaluacionProducto.getProducto().getId() == null)) {
			throw new ExeptionApi("Informe la informacion del producto! ");
		}
		if((evaluacionProducto.getPersona() == null || evaluacionProducto.getPersona().getId() == null)) {
			throw new ExeptionApi("Informe la informacion de la persona! ");
		}
		
		EvaluacionProducto registroSalvado = evaluacionProductoRepository.save(evaluacionProducto);
		
		return new ResponseEntity<EvaluacionProducto>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!evaluacionProductoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		evaluacionProductoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{productoId}/{empresaId}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<EvaluacionProducto>> buscarPorProducto(@PathVariable("productoId") Long productoId, @PathVariable("empresaId") Long empresaId) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<EvaluacionProducto> registrosBuscados = evaluacionProductoRepository.buscarPorProducto(productoId, empresaId);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<EvaluacionProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{productoId}/{personaId}/{empresaId}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<EvaluacionProducto>> buscarPorDescripcion(
			@PathVariable("productoId") Long productoId, 
			@PathVariable("personaId") Long personaId, 
			@PathVariable("empresaId") Long empresaId) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<EvaluacionProducto> registrosBuscados = evaluacionProductoRepository.buscarPorProductoPersona(productoId, personaId, empresaId);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<EvaluacionProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<EvaluacionProducto> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		EvaluacionProducto registroBuscado = evaluacionProductoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<EvaluacionProducto>(registroBuscado, HttpStatus.OK);
	
	}

}
