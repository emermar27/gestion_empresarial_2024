package com.gestion.empresarial.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
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
import com.gestion.empresarial.model.ImagenProducto;
import com.gestion.empresarial.model.dto.ImagenProductoDto;
import com.gestion.empresarial.repository.ImagenProductoRepository;

@RestController
@RequestMapping("/imagen-producto")
public class ImagenProductoController {
	
	@Autowired
	private ImagenProductoRepository imagenProductoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<ImagenProductoDto> salvar(@RequestBody @Valid ImagenProducto imagenProducto) throws ExeptionApi, MessagingException, IOException{
		
		if(imagenProducto == null) {
			throw new ExeptionApi("La Imagen Producto no puede ser NUll");
		}
		if((imagenProducto.getEmpresa() == null || imagenProducto.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece La Imagen! ");
		}
		if((imagenProducto.getProducto() == null || imagenProducto.getProducto().getId() == null)) {
			throw new ExeptionApi("Informe el Producto de la Imagen! ");
		}
				
		ImagenProducto registroSalvado = imagenProductoRepository.save(imagenProducto);
		
		ImagenProductoDto imagenProductoDto = new ImagenProductoDto();
		imagenProductoDto.setId(registroSalvado.getId());
		imagenProductoDto.setImagenOriginal(registroSalvado.getImagenOriginal());
		imagenProductoDto.setImagenMiniatura(registroSalvado.getImagenMiniatura());
		imagenProductoDto.setEmpresa(registroSalvado.getEmpresa().getId());
		imagenProductoDto.setProducto(registroSalvado.getProducto().getId());
		
		return new ResponseEntity<ImagenProductoDto>(imagenProductoDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) throws ExeptionApi { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!imagenProductoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		imagenProductoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{productoId}/{empresaId}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarTodasLasImagenesPorProducto(@PathVariable("productoId") Long productoId, @PathVariable("empresaId") Long empresaId) { /*Recibe JSON y lo comvierte para objeto*/
	
		imagenProductoRepository.eliminaTodasLasImagenesDeUnProducto(productoId, empresaId);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{productoId}/{empresaId}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<ImagenProductoDto>> buscarPorId(@PathVariable("productoId") Long productoId, @PathVariable("empresaId") Long empresaId) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<ImagenProductoDto> listaDeImagenes = new ArrayList<ImagenProductoDto>();
		
		List<ImagenProducto> registroBuscado = imagenProductoRepository.buscarPorProductoId(productoId, empresaId);
		
		if(registroBuscado.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		for(ImagenProducto imagenProductos: registroBuscado) {
			ImagenProductoDto imagenProductoDto = new ImagenProductoDto();
			imagenProductoDto.setId(imagenProductos.getId());
			imagenProductoDto.setImagenOriginal(imagenProductos.getImagenOriginal());
			imagenProductoDto.setImagenMiniatura(imagenProductos.getImagenMiniatura());
			imagenProductoDto.setEmpresa(imagenProductos.getEmpresa().getId());
			imagenProductoDto.setProducto(imagenProductos.getProducto().getId());
			listaDeImagenes.add(imagenProductoDto);
		}
		
		return new ResponseEntity<List<ImagenProductoDto>>(listaDeImagenes, HttpStatus.OK);
	
	}

}
