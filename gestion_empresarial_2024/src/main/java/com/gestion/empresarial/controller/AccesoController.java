package com.gestion.empresarial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empresarial.ExeptionApi;
import com.gestion.empresarial.model.Acceso;
import com.gestion.empresarial.repository.AccesoRepository;
import com.gestion.empresarial.service.AccesoService;

@Controller
@RestController
@RequestMapping("/acceso")
public class AccesoController {
	
	@Autowired
	private AccesoService accesoService;
	
	@Autowired
	private AccesoRepository accesoRepository;
	
	@ResponseBody
	@PostMapping(value = "/salvar") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso el resto lo omite**/
	public ResponseEntity<Acceso> salvarAcceso(@RequestBody Acceso acceso) throws ExeptionApi { /*Recibe JSON y lo comvierte para objeto*/
		
		if(acceso.getId() == null) {
			List<Acceso> accesoBuscado = accesoService.buscarAccesoPorDescripcion(acceso.getDescripcionAcceso().toUpperCase());
			if(!accesoBuscado.isEmpty()) {
				throw new ExeptionApi("Ya existe un registro de acceso con el nombre: " + acceso.getDescripcionAcceso() );
			}
		}
		
		Acceso accesoSalvar = accesoService.salvarAcceso(acceso);
		
		return new ResponseEntity<Acceso>(accesoSalvar, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@DeleteMapping(value = "/eliminar") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url eliminarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarAcceso(@RequestBody Acceso acceso) { /*Recibe JSON y lo comvierte para objeto*/
	
		accesoService.eliminarAcceso(acceso);
		
		return new ResponseEntity<>("Acceso eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarAccesoPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!accesoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		accesoService.eliminarAccesoPorId(id);
		
		return new ResponseEntity<>("Acceso eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorId/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> buscarAccesoPorId(@PathVariable("id") Long id) throws ExeptionApi { /*Recibe JSON y lo comvierte para objeto*/
	
		Acceso accesoBuscado = accesoService.buscarAccesoPorId(id);
		
		if(accesoBuscado == null) {
			throw new ExeptionApi("Acceso con ID: " + id + " no encontrado" );
		}
		
		return new ResponseEntity<Acceso>(accesoBuscado, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Acceso>> buscarAccesoPorId(@PathVariable("dato") String dato) { /*Recibe JSON y lo comvierte para objeto*/
	
		List<Acceso> accesoBuscado = accesoService.buscarAccesoPorDescripcion(dato.toUpperCase());
		
		return new ResponseEntity<List<Acceso>>(accesoBuscado, HttpStatus.OK);
	
	}

}
