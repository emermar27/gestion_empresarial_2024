package com.gestion.empresarial.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

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
import com.gestion.empresarial.model.Producto;
import com.gestion.empresarial.repository.ProductoRepository;
import com.gestion.empresarial.service.SendEmailService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private SendEmailService sendEmailService;
		
	@ResponseBody
	@PostMapping("/salvar")
	public ResponseEntity<Producto> salvar(@RequestBody @Valid Producto producto) throws ExeptionApi, MessagingException, IOException{
		
		if(producto == null) {
			throw new ExeptionApi("El producto no puede ser NUll");
		}
		if((producto.getEmpresa() == null || producto.getEmpresa().getId() == null)) {
			throw new ExeptionApi("Informe la empresa a la que petenece el Producto! ");
		}
		if((producto.getCategoria() == null || producto.getCategoria().getId() == null)) {
			throw new ExeptionApi("Informe la Categoria del Producto! ");
		}
		if((producto.getMarca() == null || producto.getMarca().getId() == null)) {
			throw new ExeptionApi("Informe la Marca del Producto! ");
		}
		if(producto.getId() == null && productoRepository.existeProductoPorNombre(producto.getNombreProducto().toUpperCase(), producto.getEmpresa().getId())) {
			throw new ExeptionApi("Ya existe un registro con la descripcion: " + producto.getNombreProducto());
		}
		if(producto.getImagenes() == null || producto.getImagenes().isEmpty() || producto.getImagenes().size() == 0) {
			throw new ExeptionApi("Producto sin imagenes: " + producto.getNombreProducto());
		}
		if(producto.getId() == null) {
			for(int x = 0; x < producto.getImagenes().size(); x++) {
				producto.getImagenes().get(x).setProducto(producto);
				producto.getImagenes().get(x).setEmpresa(producto.getEmpresa());
				
				String base64Image = "";
				
				if (producto.getImagenes().get(x).getImagenOriginal().contains("data:image")) {
					base64Image = producto.getImagenes().get(x).getImagenOriginal().split(",")[1];
				}else {
					base64Image = producto.getImagenes().get(x).getImagenOriginal();
				}
				
				byte[] imageBytes =  DatatypeConverter.parseBase64Binary(base64Image);
				
				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
				
				if (bufferedImage != null) {
					
					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					int largura = Integer.parseInt("800");
					int altura = Integer.parseInt("600");
					
					BufferedImage resizedImage = new BufferedImage(largura, altura, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, largura, altura, null);
					g.dispose();
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);
					
					String miniImgBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					
					producto.getImagenes().get(x).setImagenMiniatura(miniImgBase64);
					
					bufferedImage.flush();
					resizedImage.flush();
					baos.flush();
					baos.close();
					
				}
			}
		}
		
		Producto registroSalvado = productoRepository.save(producto);
		
		if (registroSalvado.getCantidadInventario() <= 1) {
			
			StringBuilder mensajeHtml = new StringBuilder();
			
			mensajeHtml.append("<b>Informacion Inventario de producto bajo en inventario</b><br/>");
			mensajeHtml.append("<br/>");
			mensajeHtml.append("<b>Nombre Producto: </b>" + registroSalvado.getNombreProducto()+"<br/>");
			mensajeHtml.append("<b>ID producto: </b>").append(registroSalvado.getId()).append("<br/><br/>");
			mensajeHtml.append("Gracias!");
			
			try {
				sendEmailService.enviarEmailHtml("Informacion Inventario de producto", mensajeHtml.toString(), "emermar27@hotmail.com");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<Producto>(registroSalvado, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<?> eliminarPorId(@PathVariable("id") Long id) { /*Recibe JSON y lo comvierte para objeto*/
	
		if(!productoRepository.existsById(id)) {
			return new ResponseEntity<>("Registro " + id + " no encontrado!",HttpStatus.OK);
		}
		productoRepository.deleteById(id);
		
		return new ResponseEntity<>("Registro eliminado correctamente!",HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorNombre/{dato}/{dato2}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable("dato") String dato, @PathVariable("dato2") Long empresaId) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<Producto> registrosBuscados = productoRepository.buscarPorNombre(dato, empresaId);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<Producto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorNombres/{dato}/{dato2}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Producto>> buscarPorNombres(@PathVariable("dato") String dato, @PathVariable("dato2") Long empresaId) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<Producto> registrosBuscados = productoRepository.buscarPorNombres(dato.toUpperCase(), empresaId);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<Producto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorCantidadInventario/{dato}/{dato2}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Producto>> buscarPorCantidadInventario(@PathVariable("dato") Integer dato, @PathVariable("dato2") Long dato2) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<Producto> registrosBuscados = productoRepository.buscarProductoPorCantidadInventario(dato, dato2);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<Producto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/{id}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<Producto> buscarPorId(@PathVariable("id") Long id) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		Producto registroBuscado = productoRepository.findById(id).orElse(null);
		
		if(registroBuscado == null) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<Producto>(registroBuscado, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorCategoriaId/{dato}/{dato2}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Producto>> buscarPorCategoriaId(@PathVariable("dato") Long dato, @PathVariable("dato2") Long dato2) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<Producto> registrosBuscados = productoRepository.buscarProductoPorCategoria(dato, dato2);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<Producto>>(registrosBuscados, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPorMarcaId/{dato}/{dato2}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<Producto>> buscarPorMarcaId(@PathVariable("dato") Long dato, @PathVariable("dato2") Long dato2) throws ExeptionApi{ /*Recibe JSON y lo comvierte para objeto*/
	
		List<Producto> registrosBuscados = productoRepository.buscarProductoPorMarca(dato, dato2);
		
		if(registrosBuscados.isEmpty()) {
			throw new ExeptionApi("Registro no encontrado! ");
		}
		
		return new ResponseEntity<List<Producto>>(registrosBuscados, HttpStatus.OK);
	
	}

}
