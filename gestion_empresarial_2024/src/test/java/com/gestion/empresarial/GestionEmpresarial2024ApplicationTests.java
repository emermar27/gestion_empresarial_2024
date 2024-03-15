package com.gestion.empresarial;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.empresarial.controller.AccesoController;
import com.gestion.empresarial.model.Acceso;
import com.gestion.empresarial.repository.AccesoRepository;

import junit.framework.TestCase;

@SpringBootTest(classes = GestionEmpresarial2024Application.class)
public class GestionEmpresarial2024ApplicationTests extends TestCase {
	
	@Autowired
	private AccesoRepository accesoRepository;
//	
//	@Autowired
//	private AccesoService accesoService;
	
	@Autowired
	private AccesoController accesoController;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void testRestApiRegistrarAcceso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();
		
		Acceso acceso = new Acceso();
		
		String acceso1 = "ROLE_ADMIN" + Calendar.getInstance().getTimeInMillis();
		
		acceso.setDescripcionAcceso(acceso1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/acceso/salvar")
										.content(objectMapper.writeValueAsString(acceso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno de API" + resultActions.andReturn().getResponse().getContentAsString());
		
		Acceso objetoRetorno = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				               Acceso.class);
		
		assertEquals(acceso.getDescripcionAcceso(), objetoRetorno.getDescripcionAcceso());
		
	}
	
	@Test
	public void testRestApiEliminarAcceso() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();
		
		Acceso acceso = new Acceso();
		
		acceso.setDescripcionAcceso("ROLE_ALUNNO");
		
		acceso = accesoRepository.save(acceso);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/acceso/eliminar")
										.content(objectMapper.writeValueAsString(acceso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno de API: " + resultActions.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + resultActions.andReturn().getResponse().getStatus());
		
		assertEquals("Acceso eliminado correctamente!", resultActions.andReturn().getResponse().getContentAsString());
		assertEquals(200, resultActions.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testRestApiEliminarAccesoPorId() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();
		
		Acceso acceso = new Acceso();
		
		acceso.setDescripcionAcceso("ROLE_ELIMINAR_POR_ID");
		
		acceso = accesoRepository.save(acceso);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/acceso/"+ acceso.getId())
										.content(objectMapper.writeValueAsString(acceso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno de API: " + resultActions.andReturn().getResponse().getContentAsString());
		System.out.println("Status de retorno: " + resultActions.andReturn().getResponse().getStatus());
		
		assertEquals("Acceso eliminado correctamente!", resultActions.andReturn().getResponse().getContentAsString());
		assertEquals(200, resultActions.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testRestApiBuscarAccesoPorId() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();
		
		Acceso acceso = new Acceso();
		
		acceso.setDescripcionAcceso("ROLE_BUSCAR_POR_ID");
		
		acceso = accesoRepository.save(acceso);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/acceso/buscarPorId/" + acceso.getId())
										.content(objectMapper.writeValueAsString(acceso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		assertEquals(200, resultActions.andReturn().getResponse().getStatus());
		
		Acceso accesoRetornado = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Acceso.class);
		
		assertEquals(acceso.getDescripcionAcceso(), accesoRetornado.getDescripcionAcceso());
		
	}
	
	@Test
	public void testRestApiBuscarAccesoPorDesc() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder defaultMockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = defaultMockMvcBuilder.build();
		
		Acceso acceso = new Acceso();
		
		acceso.setDescripcionAcceso("ROLE_BUSCAR_POR_DES");
		
		acceso = accesoRepository.save(acceso);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/acceso/buscarPorDescripcion/POR_DES")
										.content(objectMapper.writeValueAsString(acceso))
										.accept(MediaType.APPLICATION_JSON)
										.contentType(MediaType.APPLICATION_JSON));
		
		assertEquals(200, resultActions.andReturn().getResponse().getStatus());
		
		List<Acceso> accesoRetornadoList = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), 
				new TypeReference<List<Acceso>>() {});
		
		assertEquals(acceso.getDescripcionAcceso(), accesoRetornadoList.get(0).getDescripcionAcceso());
		
		accesoRepository.deleteById(acceso.getId());
		
	}
	

	@Test
	public void testCrearRegistroAcceso() throws ExeptionApi {
		
		Acceso acceso = new Acceso();
		
		String acceso1 = "ROLE_ADMIN" + Calendar.getInstance().getTimeInMillis();
		
		acceso.setDescripcionAcceso(acceso1);
		
		//Valida si no es null
		assertEquals(true, acceso.getId() == null);
		
		//Guarda en BD
		acceso = accesoController.salvarAcceso(acceso).getBody();
		
		assertEquals(true, acceso.getId() > 0);
		
		//Valida si se guarda el dato enviado
		assertEquals(acceso1, acceso.getDescripcionAcceso());
		
		//Test de cargue de dato guardado
		
		Acceso acceso2 = accesoRepository.findById(acceso.getId()).get();
		
		assertEquals(acceso.getId(), acceso2.getId());
		
		java.util.List<Acceso> accesos = accesoRepository.buscarAccesoPorDescripcion(acceso1.trim().toUpperCase());
		
		assertEquals(1, accesos.size());
		
		accesoRepository.deleteById(acceso.getId());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
