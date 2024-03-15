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
import com.gestion.empresarial.model.CategoriaProducto;
import com.gestion.empresarial.model.dto.CategoriaProductoDto;
import com.gestion.empresarial.repository.CategoriaProductoRepository;

@RestController
@RequestMapping("/categoria-producto")
public class CategoriaProductoController {
	
	@Autowired
	private CategoriaProductoRepository categoriaProductoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<CategoriaProductoDto> salvar(@RequestBody @Valid CategoriaProducto categoriaProducto) throws ExeptionApi{
		
		if(categoriaProducto == null) {
			throw new ExeptionApi("Categoria de producto no puede ser NUll");
		}
		if(categoriaProducto.getDescripcionCategoria() == null) {
			throw new ExeptionApi("Por favor informe la descripcion de la categoria");
		}
		if(categoriaProducto.getId() == null && categoriaProductoRepository.buscarCategoriaPorNombre(categoriaProducto.getDescripcionCategoria().toUpperCase())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + categoriaProducto.getDescripcionCategoria());
		}
		if((categoriaProducto.getEmpresa() == null || categoriaProducto.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece la Categoria de producto! ");
		}
		
		CategoriaProducto categoriaProductoSalvada = categoriaProductoRepository.save(categoriaProducto);
		
		CategoriaProductoDto  categoriaProductoDto = new CategoriaProductoDto();
		categoriaProductoDto.setId(categoriaProductoSalvada.getId());
		categoriaProductoDto.setDescripcionCategoria(categoriaProductoSalvada.getDescripcionCategoria());
		categoriaProductoDto.setEmpresa(categoriaProductoSalvada.getEmpresa().getId().toString());
		
		
		return new ResponseEntity<CategoriaProductoDto>(categoriaProductoDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!categoriaProductoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		categoriaProductoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorDescripcion/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<CategoriaProducto>> buscarPorDescripcion(@PathVariable("dato") String dato) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<CategoriaProducto> registrosBuscados = categoriaProductoRepository.buscarCategoriaProductoPorDescripcion(dato.toUpperCase());
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<CategoriaProducto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<CategoriaProducto> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		CategoriaProducto registroBuscado = categoriaProductoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<CategoriaProducto>(registroBuscado, HttpStatus.OK);
	
	}

}
