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
import com.gestion.empresarial.model.MarcaProducto;
import com.gestion.empresarial.repository.MarcaProductoRepository;

@RestController
@RequestMapping("/marca-producto")
public class MarcaProductoController {
	
	@Autowired
	private MarcaProductoRepository marcaProductoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<MarcaProducto> salvar(@RequestBody @Valid MarcaProducto marcaProducto) throws ExeptionApi{
		
		if(marcaProducto == null) {
			throw new ExeptionApi("La Marca no puede ser NUll");
		}
		if(marcaProducto.getDescripcionMarca() == null) {
			throw new ExeptionApi("Por favor informe la descripcion de la marca");
		}
		if(marcaProducto.getId() == null && marcaProductoRepository.existeMarcaPorNombre(marcaProducto.getDescripcionMarca().toUpperCase(), marcaProducto.getEmpresa().getId())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + marcaProducto.getDescripcionMarca());
		}
		if((marcaProducto.getEmpresa() == null || marcaProducto.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Marca del producto! ");
		}
		
		MarcaProducto registroSalvado = marcaProductoRepository.save(marcaProducto);
		
		return new ResponseEntity<MarcaProducto>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!marcaProductoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		marcaProductoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<MarcaProducto>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<MarcaProducto> registrosBuscados = marcaProductoRepository.buscarMarcaPorNombre(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<MarcaProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<MarcaProducto> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		MarcaProducto registroBuscado = marcaProductoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<MarcaProducto>(registroBuscado, HttpStatus.OK);
	
	}

}
